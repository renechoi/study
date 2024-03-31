package org.example.javaconcurrencyproblem.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author : Rene Choi
 * @since : 2024/03/21
 */
public class IdentifierLockManager {
	private static final ConcurrentHashMap<String, Long> identifierStore = new ConcurrentHashMap<>();

	/**
	 * 기존에 식별자가 없다면 삽입하고 true 반환
	 * @param identifier
	 * @return
	 */
	public static boolean tryLock(String identifier) {
		return identifierStore.putIfAbsent(identifier, System.currentTimeMillis()) == null;
	}

	public static void unlock(String identifier) {
		identifierStore.remove(identifier);
	}

	/**
	 * 스케줄링을 사용하여 캐시 만료 정책 구현
	 */
	public static void checkAndCleanExpiredIdentifiers() {
		long currentTime = System.currentTimeMillis();
		identifierStore.entrySet().removeIf(entry -> currentTime - entry.getValue() > TimeUnit.SECONDS.toMillis(5));
	}
}
