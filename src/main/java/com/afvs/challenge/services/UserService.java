package com.afvs.challenge.services;

import com.afvs.challenge.models.dtos.LoginDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    LoginDto login(@RequestParam("username") String username, @RequestParam("password") String password);

    List getAllUsers();

    List getUsersMoreVotes(Integer numberMonth);
}
