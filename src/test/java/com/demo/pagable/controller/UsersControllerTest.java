package com.demo.pagable.controller;

import com.demo.pagable.enums.UserStatus;
import com.demo.pagable.model.Users;
import com.demo.pagable.routes.UsersRoute;
import com.demo.pagable.service.UsersService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/*
 @Author Milan Paudyal
 @Date 5/4/19, Sat
*/
public class UsersControllerTest {

    @Mock
    private UsersService usersService;
    @InjectMocks
    private UsersController usersController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //there is no need to add RunWith... if we add this line of code which enables mockito annotations
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usersController)
                .build();
    }

    @Test
    public void testSaveUsers() throws Exception {
        List<Users> usersList = getUserList();
        Mockito.doNothing().when(usersService).saveAllUsers(usersList);
        mockMvc.perform(MockMvcRequestBuilders.get(UsersRoute.ADD_USERS))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetUsers() throws Exception {
        List<Users> usersList = getUserList();
        Mockito.when(usersService.getAllUsers())
                .thenReturn(usersList);
        //$[0].name fetch the name of user of index 0 in the list of users
        mockMvc.perform(get(UsersRoute.GET_USERS))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("User0")));
    }

    private List<Users> getUserList() {
        List<Users> users = new ArrayList<Users>();
        for (int i = 0; i < 5; i++) {
            Users user = new Users();
            user.setAge(i);
            user.setName("User" + i);
            user.setUserStatus(UserStatus.ACTIVE);
            users.add(user);
        }
        return users;
    }
}