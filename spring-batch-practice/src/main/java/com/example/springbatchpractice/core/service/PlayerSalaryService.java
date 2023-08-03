package com.example.springbatchpractice.core.service;

import java.time.Year;

import org.springframework.stereotype.Service;

import com.example.springbatchpractice.dto.PlayerDto;
import com.example.springbatchpractice.dto.PlayerSalaryDto;

@Service
public class PlayerSalaryService {

	public PlayerSalaryDto calcSalary(PlayerDto player) {
		int salary = (Year.now().getValue() - player.getBirthYear()) * 1000000;
		return PlayerSalaryDto.of(player, salary);
	}
}
