package org.designpattern.strategy;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class StrategyPatternMain {

	@Test
	public static void test() throws IOException {
		System.out.println("전화 상담 할당 방식을 선택 하세요.");
		System.out.println("R : 한명씩 차례로 할당 ");
		System.out.println("L : 쉬고 있거나 대기가 가장 적은 상담원에게 할당 ");
		System.out.println("P : 우선순위가 높은 고객 먼저 할당 ");
		System.out.println("A : 상담원이 상담 가져가기 ");

		int ch = System.in.read();
		Scheduler scheduler = null;

		if(ch == 'R' || ch == 'r'){
			scheduler = new RoundRobin();
		}
		else if(ch == 'L' || ch == 'l'){
			scheduler = new LeastJob();
		}
		else if(ch == 'P'|| ch == 'p'){
			scheduler = new PriorityAllocation();
		}
		else if(ch == 'A'|| ch == 'a'){
			scheduler = new AgentGetCall();
		}
		else{
			System.out.println("지원되지 않는 기능입니다.");
			return;
		}

		scheduler.getNextCall();
		scheduler.sendCallToAgent();
	}

	public static void test2() throws IOException {
		System.out.println("전화 상담 할당 방식을 선택 하세요.");
		System.out.println("R : 한명씩 차례로 할당 ");
		System.out.println("L : 쉬고 있거나 대기가 가장 적은 상담원에게 할당 ");
		System.out.println("P : 우선순위가 높은 고객 먼저 할당 ");
		System.out.println("A : 상담원이 상담 가져가기 ");

		int ch = System.in.read();
		CallCenter callCenter = new CallCenter();
		callCenter.handleRequest((char) ch);
	}
	public static void main(String[] args) throws IOException {
		// test();
		test2();
	}
}
