package com.shiftO.controller;

import com.shiftO.dto.EmployeeDTO;
import com.shiftO.service.HRService;
import com.shiftO.dto.ScheduleDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
public class HRController {

    private final HRService hrService;

    @Autowired
    public HRController(HRService hrService) {
        this.hrService = hrService;
    }

    @GetMapping("/employee-info")
    public ResponseEntity<?> viewEmployeeInfo(@RequestParam("employeeId") Long employeeId) {
        try {
            EmployeeDTO employeeInfo = hrService.viewEmployeeInfo(employeeId);
            return ResponseEntity.ok(employeeInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update-employee-info")
    public ResponseEntity<?> updateEmployeeInfo(@RequestBody EmployeeDTO employeeDTO) {
        try {
            hrService.updateEmployeeInfo(employeeDTO);
            return ResponseEntity.ok("Employee information updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/schedule/{employeeId}")
    public ResponseEntity<List<ScheduleDTO>> getEmployeeSchedule(@PathVariable Long employeeId) {
        List<ScheduleDTO> schedules = hrService.getEmployeeSchedule(employeeId);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping("/createSchedule")
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        hrService.createSchedule(scheduleDTO);
        return ResponseEntity.ok("Schedule created successfully.");
    }

    @PostMapping("/approve-request")
    public ResponseEntity<?> approveScheduleChange(@RequestParam("requestId") Long requestId) {
        try {
            hrService.approveScheduleChange(requestId);
            return ResponseEntity.ok("Schedule change request approved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reject-request")
    public ResponseEntity<?> rejectScheduleChange(@RequestParam("requestId") Long requestId) {
        try {
            hrService.rejectScheduleChange(requestId);
            return ResponseEntity.ok("Schedule change request rejected.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}