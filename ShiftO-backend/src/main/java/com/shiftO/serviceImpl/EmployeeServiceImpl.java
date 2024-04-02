package com.shiftO.serviceImpl;

import com.shiftO.dto.ScheduleDTO;
import com.shiftO.model.Schedule;
import com.shiftO.repository.ScheduleRepository;
import com.shiftO.service.EmployeeService;
import com.shiftO.model.ScheduleRequest;
import com.shiftO.model.ScheduleRequest.Status;
import com.shiftO.repository.ScheduleRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleRequestRepository scheduleRequestRepository;

    @Autowired
    public EmployeeServiceImpl(ScheduleRepository scheduleRepository, ScheduleRequestRepository scheduleRequestRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleRequestRepository = scheduleRequestRepository;
    }

    @Override
    public List<ScheduleDTO> viewSchedule(Long employeeId) {
        List<Schedule> schedules = scheduleRepository.findByEmployeeId(employeeId);
        // return schedules.stream().map(schedule -> {
        //     ScheduleDTO dto = new ScheduleDTO();
        //     // Map schedule to ScheduleDTO
        //     return dto;
        // }).collect(Collectors.toList());
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
    public void requestScheduleChange(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(scheduleDTO.getId())
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + scheduleDTO.getId()));
        ScheduleRequest request = new ScheduleRequest();
        request.setSchedule(schedule);
        request.setRequestedStartTime(scheduleDTO.getStartTime());
        request.setRequestedEndTime(scheduleDTO.getEndTime());
        request.setStatus(com.shiftO.model.ScheduleRequest.Status.Pending);
        scheduleRequestRepository.save(request);
    }
}