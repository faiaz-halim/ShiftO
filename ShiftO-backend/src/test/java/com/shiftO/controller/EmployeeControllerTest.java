// package com.shiftO.controller;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.shiftO.dto.ScheduleDTO;
// import com.shiftO.model.Employee;
// import com.shiftO.repository.EmployeeRepository;
// import com.shiftO.service.EmployeeService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.time.LocalDateTime;
// import java.util.Collections;

// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.BDDMockito.given;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest(EmployeeController.class)
// public class EmployeeControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private EmployeeRepository employeeRepository;

//     @MockBean
//     private EmployeeService employeeService;

//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @Test
//     public void viewScheduleTest() throws Exception {
//         Employee employee = employeeRepository.findById(1L)
//                 .orElseThrow(() -> new RuntimeException("Employee not found with id: " + 1L));
//         ScheduleDTO mockSchedule = new ScheduleDTO();
//         mockSchedule.setId(1L);
//         mockSchedule.setEmployee(employee);
//         mockSchedule.setStartTime(LocalDateTime.of(2023, 10, 10, 9, 0));
//         mockSchedule.setEndTime(LocalDateTime.of(2023, 10, 10, 17, 0));
//         mockSchedule.setStatus("Approved");

//         given(employeeService.viewSchedule(anyLong())).willReturn(Collections.singletonList(mockSchedule));

//         mockMvc.perform(get("/employee/viewSchedule?employeeId=1")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                 .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(mockSchedule))));
//     }

//     @Test
//     public void requestScheduleChangeTest() throws Exception {
//         Employee employee = employeeRepository.findById(1L)
//                 .orElseThrow(() -> new RuntimeException("Employee not found with id: " + 1L));
//         ScheduleDTO scheduleChangeRequest = new ScheduleDTO();
//         scheduleChangeRequest.setEmployee(employee);
//         scheduleChangeRequest.setStartTime(LocalDateTime.of(2023, 10, 11, 9, 0));
//         scheduleChangeRequest.setEndTime(LocalDateTime.of(2023, 10, 11, 17, 0));
//         scheduleChangeRequest.setStatus("Pending");

//         String jsonRequest = objectMapper.writeValueAsString(scheduleChangeRequest);

//         mockMvc.perform(post("/employee/requestScheduleChange")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(jsonRequest))
//                 .andExpect(status().isOk());
//     }
// }
