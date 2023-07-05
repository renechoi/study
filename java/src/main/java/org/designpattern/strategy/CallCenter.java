package org.designpattern.strategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CallCenter {
    private Map<Character, Scheduler> schedulerMap;

    public CallCenter() {
        schedulerMap = new HashMap<>();
        schedulerMap.put('R', new RoundRobin());
        schedulerMap.put('L', new LeastJob());
        schedulerMap.put('P', new PriorityAllocation());
        schedulerMap.put('A', new AgentGetCall());
    }

    public void handleRequest(char option) {
        Scheduler scheduler = schedulerMap.get(Character.toUpperCase(option));
        if (scheduler != null) {
            scheduler.getNextCall();
            scheduler.sendCallToAgent();
        } else {
            System.out.println("지원되지 않는 기능입니다.");
        }
    }
}


