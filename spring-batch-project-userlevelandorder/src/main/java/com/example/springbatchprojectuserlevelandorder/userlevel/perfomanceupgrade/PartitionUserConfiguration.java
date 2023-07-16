package com.example.springbatchprojectuserlevelandorder.userlevel.perfomanceupgrade;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;

import com.example.springbatchprojectuserlevelandorder.order.JobParametersDecide;
import com.example.springbatchprojectuserlevelandorder.order.OrderStatistics;
import com.example.springbatchprojectuserlevelandorder.userlevel.LevelUpJobExecutionListener;
import com.example.springbatchprojectuserlevelandorder.userlevel.SaveUserTasklet;
import com.example.springbatchprojectuserlevelandorder.userlevel.User;
import com.example.springbatchprojectuserlevelandorder.userlevel.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class PartitionUserConfiguration {

	private final String JOB_NAME = "partitionUserJob";
	private final int CHUNK = 1000;
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final UserRepository userRepository;
	private final EntityManagerFactory entityManagerFactory;
	private final DataSource dataSource;
	private final TaskExecutor taskExecutor;

	@Bean(JOB_NAME)
	public Job userJob() throws Exception {
		return this.jobBuilderFactory.get(JOB_NAME)
			.incrementer(new RunIdIncrementer())
			.start(this.saveUserStep())
			.next(this.userLevelUpManagerStep())
			.listener(new LevelUpJobExecutionListener(userRepository))
			.next(new JobParametersDecide("date"))
			.on(JobParametersDecide.CONTINUE.getName())
			.to(this.orderStatisticsStep(null))
			.build()
			.build();
	}

	@Bean(JOB_NAME + "_orderStatisticsStep")
	@JobScope
	public Step orderStatisticsStep(@Value("#{jobParameters[date]}") String date) throws Exception {
		return this.stepBuilderFactory.get(JOB_NAME + "_orderStatisticsStep")
			.<OrderStatistics, OrderStatistics>chunk(CHUNK)
			.reader(orderStatisticsItemReader(date))
			.writer(orderStatisticsItemWriter(date))
			.build();
	}

	private ItemWriter<? super OrderStatistics> orderStatisticsItemWriter(String date) throws Exception {
		YearMonth yearMonth = YearMonth.parse(date);
		String fileName = yearMonth.getYear() + "년_" + yearMonth.getMonthValue() + "월_일별_주문_금액.csv";

		BeanWrapperFieldExtractor<OrderStatistics> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] {"amount", "date"});

		DelimitedLineAggregator<OrderStatistics> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(",");
		lineAggregator.setFieldExtractor(fieldExtractor);

		FlatFileItemWriter<OrderStatistics> itemWriter = new FlatFileItemWriterBuilder<OrderStatistics>().resource(
				new FileSystemResource("output/" + fileName))
			.lineAggregator(lineAggregator)
			.name(JOB_NAME + "_orderStatisticsItemWriter")
			.encoding("UTF-8")
			.headerCallback(writer -> writer.write("total_amoun,date"))
			.build();
		itemWriter.afterPropertiesSet();

		return itemWriter;
	}

	private ItemReader<? extends OrderStatistics> orderStatisticsItemReader(String date) throws Exception {
		YearMonth yearMonth = YearMonth.parse(date);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("startDate", yearMonth.atDay(1));
		parameters.put("endDate", yearMonth.atEndOfMonth());

		Map<String, Order> sortKey = new HashMap<>();
		sortKey.put("created_date", Order.ASCENDING);

		JdbcPagingItemReader<OrderStatistics> itemReader = new JdbcPagingItemReaderBuilder<OrderStatistics>().dataSource(
				this.dataSource)
			.rowMapper((resultSet, i) -> OrderStatistics.builder()
				.amount(resultSet.getString(1))
				.date(LocalDate.parse(resultSet.getString(2), DateTimeFormatter.ISO_DATE))
				.build())
			.pageSize(CHUNK)
			.name(JOB_NAME + "_orderStatisticsItemReader")
			.selectClause("sum(amount), created_date")
			.fromClause("orders")
			.whereClause("created_date >= :startDate and created_date <= :endDate")
			.groupClause("created_date")
			.parameterValues(parameters)
			.sortKeys(sortKey)
			.build();
		itemReader.afterPropertiesSet();
		return itemReader;
	}

	@Bean(JOB_NAME + "_saveUserStep")
	public Step saveUserStep() {
		return this.stepBuilderFactory.get(JOB_NAME + "_saveUserStep")
			.tasklet(new SaveUserTasklet(userRepository))
			.build();
	}

	/**
	 * 마스터 스텝
	 */
	@Bean(JOB_NAME + "_userLevelUpStep.manager")
	public Step userLevelUpManagerStep() throws Exception {
		return this.stepBuilderFactory.get(JOB_NAME + "_userLevelUpStep.manager")
			.partitioner(JOB_NAME + "_userLevelUpStep", new UserLevelUpPartitioner(userRepository))
			.step(userLevelUpStep())
			.partitionHandler(taskExecutorPartitionHandler())
			.build();
	}

	/**
	 * 슬레이브 스텝
	 */
	@Bean(JOB_NAME + "_userLevelUpStep")
	public Step userLevelUpStep() throws Exception {
		return this.stepBuilderFactory.get(JOB_NAME + "_userLevelUpStep")
			.<User, Future<User>>chunk(CHUNK)
			.reader(itemReader(null, null))
			.processor(itemProcessor())
			.writer(itemWriter())
			.build();
	}

	/**
	 * 핸들러에 슬레이브 스텝을 설정하고 그리드 사이즈를 설정한다.
	 * 그리드 사이즈는 파티션 사이즈와 동일할다 -> 파티션을 핸들링하는 것
	 */
	@Bean(JOB_NAME + "_taskExecutorPartitionHandler")
	PartitionHandler taskExecutorPartitionHandler() throws Exception {
		TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
		handler.setStep(userLevelUpStep());
		handler.setTaskExecutor(this.taskExecutor);
		handler.setGridSize(8);

		return handler;
	}

	private AsyncItemWriter<User> itemWriter() {
		ItemWriter<User> itemWriter = users -> users.forEach(x -> {
			x.levelUp();
			userRepository.save(x);
		});

		AsyncItemWriter<User> asyncItemWriter = new AsyncItemWriter<>();
		asyncItemWriter.setDelegate(itemWriter);

		return asyncItemWriter;
	}

	private AsyncItemProcessor<User, User> itemProcessor() {
		ItemProcessor<User, User> itemProcessor = user -> {
			if (user.availableLeveUp()) {
				return user;
			}

			return null;
		};

		AsyncItemProcessor<User, User> asyncItemProcessor = new AsyncItemProcessor<>();
		asyncItemProcessor.setDelegate(itemProcessor);
		asyncItemProcessor.setTaskExecutor(this.taskExecutor);

		return asyncItemProcessor;
	}

	/**
	 * UserLevelUpPartitioner에서 생성된 minId와 maxId를 주입받는다.
	 * between 키워드로 min ~ max를 조회
	 *
	 * 프록시로 스텝 스코프가 생성되기 때문에 정확한 리턴타입으로 명시해주어야 한다.
	 * @param minId
	 * @param maxId
	 * @return
	 * @throws Exception
	 */
	@Bean
	@StepScope
	JpaPagingItemReader<? extends User> itemReader(@Value("#{stepExecutionContext[minId]}") Long minId,
		@Value("#{stepExecutionContext[maxId]}") Long maxId) throws Exception {

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("minId", minId);
		parameters.put("maxId", maxId);

		JpaPagingItemReader<User> itemReader = new JpaPagingItemReaderBuilder<User>().queryString(
				"select u from User u where u.id between :minId and :maxId")
			.parameterValues(parameters)
			.entityManagerFactory(entityManagerFactory)
			.pageSize(CHUNK)
			.name(JOB_NAME + "_userItemReader")
			.build();

		itemReader.afterPropertiesSet();

		return itemReader;
	}
}