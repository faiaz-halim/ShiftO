package com.shiftO.service;

import com.shiftO.dto.EmployeeDTO;
import com.shiftO.dto.ScheduleDTO;

import java.util.List;

public interface AdminService {
    void registerEmployee(EmployeeDTO employeeDTO);
    void assignSchedule(ScheduleDTO scheduleDTO);
    List<ScheduleDTO> viewAllSchedules();
    void approveSchedule(Long scheduleId);
    void approveScheduleChange(Long requestId);
    void rejectScheduleChange(Long requestId);
    void createInitialAdmin(EmployeeDTO employeeDTO);
}