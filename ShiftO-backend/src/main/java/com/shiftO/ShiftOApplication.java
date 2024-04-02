package com.shiftO;

import com.shiftO.dto.EmployeeDTO;
import com.shiftO.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShiftOApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiftOApplication.class, args);
    }

    @Autowired
    private AdminService adminService;

    @Bean
    CommandLineRunner run() {
        return args -> {
            adminService.createInitialAdmin(new EmployeeDTO(null, "Admin", "User", "admin@shiftO.com", "Administrator", "pass123", null));
        };
    }
}
