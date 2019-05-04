package com.demo.pagable.repository;

/*
 @Author Milan Paudyal
 @Date 4/28/19, Sun
*/

import com.demo.pagable.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query("select u from Users u where u.userStatus <> com.demo.pagable.enums.UserStatus.DELETED")
    Page<Users> getAllUsers(Pageable pageable);
}