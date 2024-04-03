// package com.shiftO.controller;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.shiftO.dto.ScheduleDTO;
// import com.shiftO.model.Employee;
// import com.shiftO.repository.EmployeeRepository;
// import com.shiftO.service.HRService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.time.LocalDateTime;
// import java.util.Collections;

// import static org.mockito.BDDMockito.given;
// import static org.mockito.ArgumentMatchers.any;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest(HRController.class)
// public class HRControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private EmployeeRepository employeeRepository;

//     @MockBean
//     private HRService hrService;

//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @Test
//     public void createScheduleTest() throws Exception {
//         Employee employee = employeeRepository.findById(1L)
//                 .orElseThrow(() -> new RuntimeException("Employee not found with id: " + 1L));
//         ScheduleDTO newScheduleDTO = new ScheduleDTO();
//         newScheduleDTO.setEmployee(employee);
//         newScheduleDTO.setStartTime(LocalDateTime.of(2023, 10, 15, 9, 0));
//         newScheduleDTO.setEndTime(LocalDateTime.of(2023, 10, 15, 17, 0));
//         newScheduleDTO.setStatus("Pending");

//         mockMvc.perform(post("/hr/createSchedule")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(newScheduleDTO)))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void getEmployeeScheduleTest() throws Exception {
//         Employee employee = employeeRepository.findById(1L)
//                 .orElseThrow(() -> new RuntimeException("Employee not found with id: " + 1L));
//         ScheduleDTO mockScheduleDTO = new ScheduleDTO();
//         mockScheduleDTO.setId(1L);
//         mockScheduleDTO.setEmployee(employee);
//         mockScheduleDTO.setStartTime(LocalDateTime.of(2023, 10, 15, 9, 0));
//         mockScheduleDTO.setEndTime(LocalDateTime.of(2023, 10, 15, 17, 0));
//         mockScheduleDTO.setStatus("Approved");

//         given(hrService.getEmployeeSchedule(any(Long.class))).willReturn(Collections.singletonList(mockScheduleDTO));

//         mockMvc.perform(get("/hr/schedule/2")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void approveScheduleChangeTest() throws Exception {
//         mockMvc.perform(post("/hr/approveScheduleChange")
//                 .param("requestId", "1")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void rejectScheduleChangeTest() throws Exception {
//         mockMvc.perform(post("/hr/rejectScheduleChange")
//                 .param("requestId", "1")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }
// }
