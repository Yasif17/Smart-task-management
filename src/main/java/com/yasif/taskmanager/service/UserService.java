package com.yasif.taskmanager.service;

import com.yasif.taskmanager.dto.RegisterRequest;
import com.yasif.taskmanager.dto.RegisterResponse;
import com.yasif.taskmanager.entity.User;

public interface UserService {

    RegisterResponse registerUser(RegisterRequest registerRequest);

    RegisterResponse createUserByAdmin(RegisterRequest request); // for admin

    User getUserById(Long id);

    String login(String email, String password);

}
