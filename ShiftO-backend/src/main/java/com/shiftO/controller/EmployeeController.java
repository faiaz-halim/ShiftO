package com.shiftO.controller;

import com.shiftO.dto.ScheduleDTO;
import com.shiftO.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/schedule")
    public ResponseEntity<?> viewSchedule(@RequestParam("employeeId") Long employeeId) {
        try {
            List<ScheduleDTO> schedule = employeeService.viewSchedule(employeeId);
            return ResponseEntity.ok(schedule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/request-change")
    public ResponseEntity<?> requestScheduleChange(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            employeeService.requestScheduleChange(scheduleDTO);
            return ResponseEntity.ok("Schedule change request submitted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}