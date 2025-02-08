package com.example.interntask.service.user;

import com.example.interntask.model.entity.User;

public interface UserService {

    User findUserByUserName(String username);
}