package com.shiftO.service;

import java.util.List;

import com.shiftO.dto.ScheduleDTO;

public interface EmployeeService {
    List<ScheduleDTO> viewSchedule(Long employeeId);
    void requestScheduleChange(ScheduleDTO scheduleDTO);
}