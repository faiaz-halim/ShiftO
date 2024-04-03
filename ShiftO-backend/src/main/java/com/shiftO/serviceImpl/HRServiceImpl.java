package com.shiftO.serviceImpl;

import com.shiftO.dto.EmployeeDTO;
import com.shiftO.model.Employee;
import com.shiftO.model.Schedule;
import com.shiftO.repository.EmployeeRepository;
import com.shiftO.repository.ScheduleRepository;
import com.shiftO.service.HRService;
import com.shiftO.model.ScheduleRequest;
import com.shiftO.repository.ScheduleRequestRepository;

import com.shiftO.dto.ScheduleDTO;

import com.shiftO.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HRServiceImpl implements HRService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ScheduleRequestRepository scheduleRequestRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    public HRServiceImpl(EmployeeRepository employeeRepository, ScheduleRepository scheduleRepository, ScheduleRequestRepository scheduleRequestRepository) {
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleRequestRepository = scheduleRequestRepository;
    }

    @Override
    public EmployeeDTO viewEmployeeInfo(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        return new EmployeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getRole().toString(), null, null);
    }

    @Override
    public void updateEmployeeInfo(EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(employeeDTO.getId());
        if (employee.isPresent()) { // not implemented
        }
    }

@Override
    public List<ScheduleDTO> getEmployeeSchedule(Long employeeId) {
        List<Schedule> schedules = scheduleRepository.findByEmployeeId(employeeId);

        return schedules.stream().map(schedule -> {
            ScheduleDTO dto = new ScheduleDTO();
            dto.setId(schedule.getId());
            dto.setEmployee(schedule.getEmployee());
            dto.setStartTime(schedule.getStartTime());
            dto.setEndTime(schedule.getEndTime());
            dto.setStatus(schedule.getStatus().toString());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void createSchedule(ScheduleDTO scheduleDTO) {
        Employee employee = employeeRepository.findById(scheduleDTO.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + scheduleDTO.getEmployee().getId()));

        Schedule schedule = new Schedule();
        schedule.setEmployee(employee);
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setEndTime(scheduleDTO.getEndTime());
        schedule.setStatus(Schedule.Status.valueOf(scheduleDTO.getStatus()));

        scheduleRepository.save(schedule);
    }

    @Override
    public void approveScheduleChange(Long requestId) {
        ScheduleRequest request = scheduleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("ScheduleRequest not found with id: " + requestId));

        Schedule schedule = request.getSchedule();
        schedule.setStartTime(request.getRequestedStartTime());
        schedule.setEndTime(request.getRequestedEndTime());
        schedule.setStatus(com.shiftO.model.Schedule.Status.Approved);
        scheduleRepository.save(schedule);

        request.setStatus(com.shiftO.model.ScheduleRequest.Status.Approved);
        scheduleRequestRepository.save(request);
    }

    @Override
    public void rejectScheduleChange(Long requestId) {
        ScheduleRequest request = scheduleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("ScheduleRequest not found with id: " + requestId));

        request.setStatus(com.shiftO.model.ScheduleRequest.Status.Rejected);
        scheduleRequestRepository.save(request);
    }
}
