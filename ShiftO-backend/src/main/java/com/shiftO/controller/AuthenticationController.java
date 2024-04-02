package com.shiftO.controller;

import com.shiftO.dto.EmployeeDTO;
import com.shiftO.dto.LoginDTO;
import com.shiftO.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<EmployeeDTO> login(@RequestBody LoginDTO loginDTO) {
        EmployeeDTO employeeDTO = authenticationService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return ResponseEntity.ok(employeeDTO);
    }
}
