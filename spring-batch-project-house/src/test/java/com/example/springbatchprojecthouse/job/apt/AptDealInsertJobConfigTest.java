package com.example.springbatchprojecthouse.job.apt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.springbatchprojecthouse.BatchTestConfig;
import com.example.springbatchprojecthouse.adapter.ApartmentApiResource;
import com.example.springbatchprojecthouse.core.repository.LawdRepository;
import com.example.springbatchprojecthouse.core.service.AptDealService;

@SpringBatchTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {AptDealInsertJobConfig.class, BatchTestConfig.class})
public class AptDealInsertJobConfigTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@MockBean
	private AptDealService aptDealService;

	@MockBean
	private LawdRepository lawdRepository;

	@MockBean
	private ApartmentApiResource apartmentApiResource;

	@Test
	public void success() throws Exception {
		// given
		// -> 실제 api를 매번 호출하기 보다 모킹된 api를 xml 형태로 만들어 호출하도록 한다.

		when(lawdRepository.findDistinctGuLawdCd()).thenReturn(Arrays.asList("41135", "41136"));
		when(apartmentApiResource.getResource(anyString(), any())).thenReturn(
			new ClassPathResource("test-api-response.xml"));

		// when -> 날짜를 파라미터로 받는 job 실행
		JobExecution execution = jobLauncherTestUtils.launchJob(
			new JobParameters(Maps.newHashMap("yearMonth", new JobParameter("2021-07"))));

		// then
		// -> 모킹한 xml 파일에 대한 완료 메시지 리턴
		// -> 2x3으로 6번 호출
		assertEquals(execution.getExitStatus(), ExitStatus.COMPLETED);
		verify(aptDealService, times(6)).upsert(any());
	}

	@Test
	public void fail_whenYearMonthNotExist() throws Exception {
		// given
		when(lawdRepository.findDistinctGuLawdCd()).thenReturn(Arrays.asList("41135"));
		when(apartmentApiResource.getResource(anyString(), any())).thenReturn(
			new ClassPathResource("test-api-response.xml"));

		// when -> 파라미터가 없으므로 실패
		assertThrows(JobParametersInvalidException.class, () -> jobLauncherTestUtils.launchJob());

		// then
		verify(aptDealService, never()).upsert(any());
	}
}
