package com.demo.pagable.service;

import com.demo.pagable.model.Users;

import java.util.List;

/*
 @Author Milan Paudyal
 @Date 4/28/19, Sun
*/
public interface UsersService {
    List<Users> getAllUsers();

    void saveAllUsers(List<Users> usersList);
}
