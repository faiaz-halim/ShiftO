package com.shiftO.service;

import java.util.List;

import com.shiftO.dto.EmployeeDTO;
import com.shiftO.dto.ScheduleDTO;

public interface HRService {
    EmployeeDTO viewEmployeeInfo(Long employeeId);
    void updateEmployeeInfo(EmployeeDTO employeeDTO);
    List<ScheduleDTO> getEmployeeSchedule(Long employeeId);
    void createSchedule(ScheduleDTO scheduleDTO);
    void approveScheduleChange(Long requestId);
    void rejectScheduleChange(Long requestId);
}