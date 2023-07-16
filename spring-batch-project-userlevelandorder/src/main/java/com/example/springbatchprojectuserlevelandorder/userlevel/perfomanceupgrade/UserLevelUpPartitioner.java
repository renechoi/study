package com.example.springbatchprojectuserlevelandorder.userlevel.perfomanceupgrade;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

import com.example.springbatchprojectuserlevelandorder.userlevel.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserLevelUpPartitioner implements Partitioner {
	private final UserRepository userRepository;

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		long minId = userRepository.findMinId(); // 파티셔닝 할 id의 가장 작은 -> 1
		long maxId = userRepository.findMaxId(); // 40,000

		long targetSize = (maxId - minId) / gridSize + 1; // 각 스텝에서 처리할 개수 -> 5000

		/**
		 * partition0 : 1, 5000
		 * partition1 : 5001, 10000
		 * ...
		 * partition7 : 35001, 40000
		 */
		Map<String, ExecutionContext> result = new HashMap<>();

		long number = 0;
		long start = minId;
		long end = start + targetSize - 1;

		while (start <= maxId) {
			ExecutionContext value = new ExecutionContext();

			result.put("partition" + number, value);

			if (end >= maxId) {
				end = maxId;
			}

			value.putLong("minId", start);
			value.putLong("maxId", end);

			start += targetSize;
			end += targetSize;
			number++;
		}

		return result;
	}
}