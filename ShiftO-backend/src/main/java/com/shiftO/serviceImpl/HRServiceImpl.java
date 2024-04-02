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
import java.util.Optional; // Correct import for Optional
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

        // Use the constructor with the correct arguments
        return new EmployeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getRole().toString(), null, null);
    }

    @Override
    public void updateEmployeeInfo(EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(employeeDTO.getId());
        if (employee.isPresent()) {
            // Update employee fields from EmployeeDTO and save using employeeRepository
        }
    }

@Override
    public List<ScheduleDTO> getEmployeeSchedule(Long employeeId) {
        // Fetch schedules for the employee
        List<Schedule> schedules = scheduleRepository.findByEmployeeId(employeeId);

        // Convert each Schedule entity to a ScheduleDTO
        return schedules.stream().map(schedule -> {
            ScheduleDTO dto = new ScheduleDTO();
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

        // Assuming we directly modify and approve the original schedule
        Schedule schedule = request.getSchedule();
        schedule.setStartTime(request.getRequestedStartTime());
        schedule.setEndTime(request.getRequestedEndTime());
        schedule.setStatus(com.shiftO.model.Schedule.Status.Approved); // Adjust according to your enum
        scheduleRepository.save(schedule);

        // Optionally, update the request status to Approved if needed
        request.setStatus(com.shiftO.model.ScheduleRequest.Status.Approved); // Adjust according to your enum
        scheduleRequestRepository.save(request);
    }

    @Override
    public void rejectScheduleChange(Long requestId) {
        ScheduleRequest request = scheduleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("ScheduleRequest not found with id: " + requestId));

        // Just set the request status to Rejected without changing the original schedule
        request.setStatus(com.shiftO.model.ScheduleRequest.Status.Rejected); // Adjust according to your enum
        scheduleRequestRepository.save(request);
    }
}
