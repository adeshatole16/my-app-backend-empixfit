package com.sport.platform.controller;

import com.sport.platform.entity.Admin;
import com.sport.platform.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Admin admin = adminService.login(username, password);

        if (admin == null) {
            return ResponseEntity.status(401).body(Map.of(
                "status", "error",
                "message", "Invalid username or password"
            ));
        }

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "role", "ADMIN",
            "name", admin.getName(),
            "username", admin.getUsername()
        ));
    }

    // One-time setup: create first admin
    @PostMapping("/setup")
    public ResponseEntity<String> setup() {
        Admin admin = adminService.createAdmin("Admin", "admin", "admin123");
        return ResponseEntity.ok("Admin created: " + admin.getUsername());
    }
}