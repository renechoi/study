package com.example.springbatchpractice.job.player;

import java.io.File;
import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.springbatchpractice.core.service.PlayerSalaryService;
import com.example.springbatchpractice.dto.PlayerDto;
import com.example.springbatchpractice.dto.PlayerSalaryDto;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class FlatFileJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flatFileJob(Step flatFileStep) {
        return jobBuilderFactory.get("flatFileJob")
            .incrementer(new RunIdIncrementer())
            .start(flatFileStep)
            .build();
    }

    @JobScope
    @Bean
    public Step flatFileStep(FlatFileItemReader<PlayerDto> playerFileItemReader,
        FlatFileItemWriter<PlayerSalaryDto> playerFileItemWriter, ItemProcessorAdapter<PlayerDto, PlayerSalaryDto> itemProcessorAdapter
    ) {
        return stepBuilderFactory.get("flatFileStep")
            .<PlayerDto, PlayerSalaryDto>chunk(5)
            .reader(playerFileItemReader)
            .processor(itemProcessorAdapter)
            .writer(playerFileItemWriter)
            .build();
    }

    @StepScope
    @Bean
    public FlatFileItemReader<PlayerDto> playerFileItemReader() {
        return new FlatFileItemReaderBuilder<PlayerDto>()
            .name("playerFileItemReader")
            .lineTokenizer(new DelimitedLineTokenizer())
            .linesToSkip(1)
            .fieldSetMapper(new PlayerFieldSetMapper())
            .resource(new FileSystemResource("spring-batch-practice/player-list.txt"))
            .build();
    }


    @StepScope
    @Bean
    public ItemProcessorAdapter<PlayerDto, PlayerSalaryDto> playerSalaryItemProcessorAdapter(
        PlayerSalaryService playerSalaryService) {
        ItemProcessorAdapter<PlayerDto, PlayerSalaryDto> adapter = new ItemProcessorAdapter<>();
        adapter.setTargetObject(playerSalaryService);
        adapter.setTargetMethod("calcSalary");
        return adapter;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<PlayerSalaryDto> playerFileItemWriter() throws IOException {
        BeanWrapperFieldExtractor<PlayerSalaryDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"ID", "firstName", "lastName", "salary"});
        fieldExtractor.afterPropertiesSet();

        DelimitedLineAggregator<PlayerSalaryDto> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter("\t");
        lineAggregator.setFieldExtractor(fieldExtractor);

        // 기존의 파일을 덮어쓴다.
        new File("player-salary-list.txt").createNewFile();
        FileSystemResource resource = new FileSystemResource("spring-batch-practice/player-salary-list.txt");

        return new FlatFileItemWriterBuilder<PlayerSalaryDto>()
            .name("playerFileItemWriter")
            .resource(resource)
            .lineAggregator(lineAggregator)
            .build();
    }




}

