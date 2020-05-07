package com.afvs.challenge.controller;

import com.afvs.challenge.models.dtos.GenericResponseMessageDto;
import com.afvs.challenge.models.dtos.LoginDto;
import com.afvs.challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public LoginDto login(@RequestParam("username") String username, @RequestParam("password") String password) {
        LoginDto loginDto = null;
        try {
            loginDto = this.userService.login(username, password);
        } catch (Exception e) {
            System.out.println("En esta linea se puede enviar a logear");
        }
        return loginDto;
    }

    @RequestMapping()
    public List getAllUsers() {
        List allUsers = null;
        try {
            allUsers = this.userService.getAllUsers();
        } catch (Exception e) {
            System.out.println("En esta linea se puede enviar a logear");
        }
        return allUsers;
    }

    @RequestMapping("votes")
    public List getUsersMoreVotes(@RequestParam(value = "numberMonth", defaultValue = "1") Integer numberMonth) {
        List allUsers = null;
        try {
            allUsers = this.userService.getUsersMoreVotes(numberMonth);
        } catch (Exception e) {
            System.out.println("En esta linea se puede enviar a logear");
        }
        return allUsers;
    }

    @RequestMapping("registers")
    public GenericResponseMessageDto getTotalUsersRegisters() {
        GenericResponseMessageDto genericResponseMessageDto = new GenericResponseMessageDto();
        try {
            genericResponseMessageDto.setMessage("El total de usuarios registrados es: " + this.userService.getAllUsers().size());
        } catch (Exception e) {
            System.out.println("En esta linea se puede enviar a logear");
        }
        return genericResponseMessageDto;
    }

}
