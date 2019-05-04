package com.demo.pagable.service.impl;

import com.demo.pagable.enums.UserStatus;
import com.demo.pagable.model.Users;
import com.demo.pagable.repository.UsersRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;

/*
 @Author Milan Paudyal
 @Date 5/4/19, Sat
*/

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceImplTest {

    @InjectMocks
    private UsersServiceImpl usersService;

    @Mock
    private UsersRepository usersRepository;

    @Test
    public void testGetAllUsers() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<Users> usersList = getUserList();
        Page<Users> usersPage = getUserPage(usersList, pageRequest);
        Mockito.when(usersRepository.getAllUsers(Matchers.any(PageRequest.class)))
                .thenReturn(usersPage);
        List<Users> resultList = usersService.getAllUsers();
        Assert.assertEquals(usersList.size(), resultList.size());
        Assert.assertEquals(usersList.get(0).getName(), resultList.get(0).getName());
    }

    @Test
    public void testSaveAllUsers() {
        List<Users> usersList = getUserList();
        Mockito.when(usersRepository.saveAll(usersList))
                .thenReturn(usersList);
        usersService.saveAllUsers(usersList);
        Mockito.verify(usersRepository, times(1))
                .saveAll(usersList);
    }

    private Page<Users> getUserPage(List<Users> usersList, PageRequest pageRequest) {
        return new PageImpl<>(usersList, pageRequest, 5);
    }

    private List<Users> getUserList() {
        List<Users> usersList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Users users = new Users();
            users.setUserStatus(UserStatus.ACTIVE);
            users.setName("Test" + i);
            users.setAge(18 + i);
            usersList.add(users);
        }
        return usersList;
    }
}