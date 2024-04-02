package com.shiftO.serviceImpl;

import com.shiftO.dto.EmployeeDTO;
import com.shiftO.dto.ScheduleDTO;
import com.shiftO.model.Employee;
import com.shiftO.model.Employee.Role;
import com.shiftO.model.Schedule;
import com.shiftO.repository.EmployeeRepository;
import com.shiftO.repository.ScheduleRepository;
import com.shiftO.service.AdminService;
import com.shiftO.model.ScheduleRequest;
import com.shiftO.repository.ScheduleRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ScheduleRequestRepository scheduleRequestRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(EmployeeRepository employeeRepository, ScheduleRepository scheduleRepository, ScheduleRequestRepository scheduleRequestRepository) {
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleRequestRepository = scheduleRequestRepository;
    }

    @Override
    public void createInitialAdmin(EmployeeDTO employeeDTO) {
        // Check if admin exists, if not, create initial admin
        employeeRepository.findByEmail(employeeDTO.getEmail()).ifPresentOrElse(
            existingUser -> {
            },
            () -> registerEmployee(employeeDTO)
        );
    }

    @Override
    public void registerEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        Role employeeRole = Role.valueOf(employeeDTO.getRole());
        employee.setRole(employeeRole);
        employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        employee.setCreatedAt(null);
        employeeRepository.save(employee);
    }

    @Override
    public void assignSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        // Populate schedule fields from scheduleDTO and save using scheduleRepository
    }

    @Override
    public List<ScheduleDTO> viewAllSchedules() {
        return scheduleRepository.findAll().stream().map(schedule -> {
            ScheduleDTO dto = new ScheduleDTO();
            dto.setEmployee(schedule.getEmployee());
            dto.setStartTime(schedule.getStartTime());
            dto.setEndTime(schedule.getEndTime());
            dto.setStatus(schedule.getStatus().toString());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void approveSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + scheduleId));

        schedule.setStatus(com.shiftO.model.Schedule.Status.Approved);
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
        schedule.setStatus(com.shiftO.model.Schedule.Status.Approved);
        scheduleRepository.save(schedule);

        // Optionally, update the request status to Approved if needed
        request.setStatus(com.shiftO.model.ScheduleRequest.Status.Approved);
        scheduleRequestRepository.save(request);
    }

    @Override
    public void rejectScheduleChange(Long requestId) {
        ScheduleRequest request = scheduleRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("ScheduleRequest not found with id: " + requestId));

        // Just set the request status to Rejected without changing the original schedule
        request.setStatus(com.shiftO.model.ScheduleRequest.Status.Rejected);
        scheduleRequestRepository.save(request);
    }
}
