package com.example.springbatchprojecthouse.job.apt;

import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.example.springbatchprojecthouse.core.repository.LawdRepository;

import lombok.RequiredArgsConstructor;

/**
 * 두개의 읽기 구 코드 가져오기 + XML 파일을 읽기를 합치기 위해 필요한 작업
 * <p>
 * ExecutionContext에 저장할 데이터
 * 1. guLawdCd - 구 코드 -> 다음 스텝에서 활용할 값
 * 2. guLawdCdList - 구 코드 리스트
 * 3. itemCount - 남아있는 구 코드의 갯수
 */
@RequiredArgsConstructor
public class GuLawdTasklet implements Tasklet {
	private final LawdRepository lawdRepository;
	private List<String> guLawdCdList;
	private int itemCount;

	private static final String KEY_ITEM_COUNT = "itemCount";
	private static final String KEY_GU_LAWD_CD_LIST = "guLawdCdList";
	private static final String KEY_GU_LAWD_CD = "guLawdCd";

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		ExecutionContext executionContext = getExecutionContext(chunkContext);
		initList(executionContext);
		initItemCount(executionContext);

		if (itemCount == 0) {
			contribution.setExitStatus(ExitStatus.COMPLETED);
			return RepeatStatus.FINISHED;
		}

		itemCount--;

		executionContext.put(KEY_GU_LAWD_CD, guLawdCdList.get(itemCount));
		executionContext.putInt(KEY_ITEM_COUNT, itemCount);

		contribution.setExitStatus(new ExitStatus("CONTINUABLE"));
		return RepeatStatus.FINISHED;
	}

	/**
	 * executionContext를 가져올 때
	 * Map<String, Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
	 * 위와 같이 가져오면 umoodifiableMap으로 가져오기 때문에 put 오퍼레이션이 불가하다.
	 * 위와 같은 방식은 단순 조회시에는 사용 가능하다.
	 */
	private ExecutionContext getExecutionContext(ChunkContext chunkContext) {
		StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
		return stepExecution.getJobExecution().getExecutionContext();
	}

	/**
	 * 최초 1회 쿼리 실행으로 리스트를 가져오고 가져온 리스트를ㅇ executionContext에 넣어서 해당 값을 활용할 수 있도록 한다.
	 * 그렇지 않으면 각각에 대해서 계속적으로 쿼리를 실행해야 한다.
	 */
	private void initList(ExecutionContext executionContext) {
		if (executionContext.containsKey(KEY_GU_LAWD_CD_LIST)) {
			guLawdCdList = (List<String>)executionContext.get(KEY_GU_LAWD_CD_LIST);
		} else {
			guLawdCdList = lawdRepository.findDistinctGuLawdCd();
			executionContext.put(KEY_GU_LAWD_CD_LIST, guLawdCdList);
			executionContext.putInt(KEY_ITEM_COUNT, guLawdCdList.size());
		}
	}

	private void initItemCount(ExecutionContext executionContext) {
		if (executionContext.containsKey(KEY_ITEM_COUNT)) {
			itemCount = executionContext.getInt(KEY_ITEM_COUNT);
		} else {
			itemCount = guLawdCdList.size();
		}
	}
}
