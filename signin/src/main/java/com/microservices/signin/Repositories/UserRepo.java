package com.microservices.signin;

import com.microservices.signin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
//    @Query("SELECT '*' from users")
//    List<User> findAll();
//    User findPasswordByUsername(String userName);
}
