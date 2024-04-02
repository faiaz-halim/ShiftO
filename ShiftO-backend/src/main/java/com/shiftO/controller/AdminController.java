package com.shiftO.controller;

import com.shiftO.dto.EmployeeDTO;
import com.shiftO.dto.ScheduleDTO;
import com.shiftO.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            adminService.registerEmployee(employeeDTO);
            return ResponseEntity.ok("Employee registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/schedule")
    public ResponseEntity<?> assignSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            adminService.assignSchedule(scheduleDTO);
            return ResponseEntity.ok("Schedule assigned successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/schedules")
    public ResponseEntity<?> viewAllSchedules() {
        try {
            return ResponseEntity.ok(adminService.viewAllSchedules());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approveSchedule(@RequestParam("scheduleId") Long scheduleId) {
        try {
            adminService.approveSchedule(scheduleId);
            return ResponseEntity.ok("Schedule approved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/approve-request")
    public ResponseEntity<?> approveScheduleChange(@RequestParam("requestId") Long requestId) {
        try {
            adminService.approveScheduleChange(requestId);
            return ResponseEntity.ok("Schedule change request approved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reject-request")
    public ResponseEntity<?> rejectScheduleChange(@RequestParam("requestId") Long requestId) {
        try {
            adminService.rejectScheduleChange(requestId);
            return ResponseEntity.ok("Schedule change request rejected.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}