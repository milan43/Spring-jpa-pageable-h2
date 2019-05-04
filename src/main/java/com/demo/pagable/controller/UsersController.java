package com.demo.pagable.controller;

/*
 @Author Milan Paudyal
 @Date 4/28/19, Sun
*/

import com.demo.pagable.enums.UserStatus;
import com.demo.pagable.model.Users;
import com.demo.pagable.routes.UsersRoute;
import com.demo.pagable.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * Here user list is provided from program itself so the api pattern and
     * request method is different from the best practices
     */
    @GetMapping(UsersRoute.ADD_USERS)
    public void saveUsers() {
        usersService.saveAllUsers(getUserList());
    }

    //api pattern /api/users/get
    @GetMapping(UsersRoute.GET_USERS)
    public List<Users> getUsers() {
        return usersService.getAllUsers();
    }


    /**
     * @return the list of random users
     */
    private List<Users> getUserList() {
        List<Users> users = new ArrayList<Users>();
        for (int i = 0; i < 100; i++) {
            Users user = new Users();
            user.setAge(i);
            user.setName("User" + i);
            user.setUserStatus(UserStatus.ACTIVE);
            users.add(user);
        }
        return users;
    }
}
