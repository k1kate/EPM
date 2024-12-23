package com.example.EPM.service;

import com.example.EPM.models.Role;
import com.example.EPM.models.User;
import com.example.EPM.repository.RoleRepository;
import com.example.EPM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(String username, String password, String roleName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);

        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    public User findUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("not found"));
        return user;

    }


}