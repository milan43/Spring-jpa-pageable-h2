package com.demo.pagable.service.impl;

import com.demo.pagable.model.Users;
import com.demo.pagable.repository.UsersRepository;
import com.demo.pagable.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 @Author Milan Paudyal
 @Date 4/28/19, Sun
*/

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * fetch the user list of 50 users at once in every 2 seconds gap
     *
     * @return the list of users fetched using pagable
     */
    @Override
    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        Page<Users> listPage = null;
        int page = 0;
        int pageSize = 50;
        do {
            listPage = usersRepository.getAllUsers(PageRequest.of(page, pageSize));
            usersList.addAll(listPage.getContent());
            page++;
            try {
                Thread.sleep(2000);
                System.out.println("Fetching data for page -> " + page);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (listPage.hasNext());
        return usersList;
    }

    /**
     * save the user list in database at once
     *
     * @param usersList to be saved
     */
    @Override
    public void saveAllUsers(List<Users> usersList) {
        usersRepository.saveAll(usersList);
    }
}
