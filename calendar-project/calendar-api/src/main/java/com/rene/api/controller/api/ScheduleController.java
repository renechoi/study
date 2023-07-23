package com.rene.api.controller.api;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rene.api.dto.AuthUser;
import com.rene.api.dto.EventCreateReq;
import com.rene.api.dto.ForListScheduleDto;
import com.rene.api.dto.NotificationCreateReq;
import com.rene.api.dto.TaskCreateReq;
import com.rene.api.service.EventService;
import com.rene.api.service.NotificationService;
import com.rene.api.service.ScheduleQueryService;
import com.rene.api.service.TaskService;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

	private final ScheduleQueryService scheduleQueryService;
	private final TaskService taskService;
	private final EventService eventService;
	private final NotificationService notificationService;

	@PostMapping("/tasks")
	public ResponseEntity<Void> createTask(@RequestBody TaskCreateReq taskCreateReq, AuthUser authUser) {
		taskService.create(taskCreateReq, authUser);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/event")
	public ResponseEntity<Void> createEvent(@RequestBody EventCreateReq eventCreateReq, AuthUser authUser) {
		eventService.create(eventCreateReq, authUser);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/notifications")
	public ResponseEntity<Void> createTask(@RequestBody NotificationCreateReq notificationCreateReq,
		AuthUser authUser) {
		notificationService.create(notificationCreateReq, authUser);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/day")
	public List<ForListScheduleDto> getSchedulesByDay(AuthUser authUser,
		@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return scheduleQueryService.getSchedulesByDay(date == null ? LocalDate.now() : date, authUser);
	}

	@GetMapping("/week")
	public List<ForListScheduleDto> getSchedulesByWeek(
		AuthUser authUser,
		@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOfWeek
	) {
		return scheduleQueryService.getSchedulesByWeek(startOfWeek == null ? LocalDate.now() : startOfWeek, authUser);
	}

	@GetMapping("/month")
	public List<ForListScheduleDto> getSchedulesByMonth(
		AuthUser authUser,
		@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") String yearMonth
	) {
		return scheduleQueryService.getSchedulesByMonth(yearMonth == null ? YearMonth.now() : YearMonth.parse(yearMonth), authUser);
	}
}