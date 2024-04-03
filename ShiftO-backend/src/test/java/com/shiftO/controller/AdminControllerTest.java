// package com.shiftO.controller;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.shiftO.controller.AdminController;
// import com.shiftO.dto.EmployeeDTO;
// import com.shiftO.dto.ScheduleDTO;
// import com.shiftO.model.Employee;
// import com.shiftO.repository.EmployeeRepository;
// import com.shiftO.service.AdminService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.time.LocalDateTime;
// import java.util.Arrays;
// import java.util.Date;

// import static org.mockito.BDDMockito.given;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest(AdminController.class)
// public class AdminControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private EmployeeRepository employeeRepository;

//     @MockBean
//     private AdminService adminService;

//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @Test
//     public void registerEmployeeTest() throws Exception {
//         EmployeeDTO employeeDTO = new EmployeeDTO(null, "John", "Doe", "john.doe@example.com", "EMPLOYEE", "password", new Date());
//         mockMvc.perform(post("/admin/registerEmployee")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(employeeDTO)))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void viewAllSchedulesTest() throws Exception {
//         Employee employee = employeeRepository.findById(1L)
//                 .orElseThrow(() -> new RuntimeException("Employee not found with id: " + 1L));
//         ScheduleDTO schedule1 = new ScheduleDTO();
//         schedule1.setId(1L);
//         schedule1.setEmployee(employee);
//         schedule1.setStartTime(LocalDateTime.now());
//         schedule1.setEndTime(LocalDateTime.now().plusHours(8));
//         schedule1.setStatus("Pending");
//         ScheduleDTO schedule2 = new ScheduleDTO();
//         schedule2.setId(2L);
//         schedule2.setEmployee(employee);
//         schedule2.setStartTime(LocalDateTime.now());
//         schedule2.setEndTime(LocalDateTime.now().plusHours(8));
//         schedule2.setStatus("Approved");

//         given(adminService.viewAllSchedules()).willReturn(Arrays.asList(schedule1, schedule2));

//         mockMvc.perform(get("/admin/viewAllSchedules")
//                         .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void approveScheduleChangeTest() throws Exception {
//         mockMvc.perform(post("/admin/approveScheduleChange")
//                         .param("requestId", "1")
//                         .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void rejectScheduleChangeTest() throws Exception {
//         mockMvc.perform(post("/admin/rejectScheduleChange")
//                         .param("requestId", "1")
//                         .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }
// }
