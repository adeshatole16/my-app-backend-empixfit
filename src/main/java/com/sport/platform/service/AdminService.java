package com.sport.platform.service;

import com.sport.platform.entity.Admin;
import com.sport.platform.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin login(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return admin;
        }
        return null;
    }

    // Use this once to create the first admin
    public Admin createAdmin(String name, String username, String rawPassword) {
        Admin admin = new Admin();
        admin.setName(name);
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(rawPassword));
        admin.setRole("ADMIN");
        return adminRepository.save(admin);
    }
}