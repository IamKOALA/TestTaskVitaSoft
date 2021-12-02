package com.company.VitaSoft.users.api.controller;

import com.company.VitaSoft.users.api.service.UserService;
import com.company.VitaSoft.users.impl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllNotes() {
        return userService.getUserList();
    }

    @PostMapping("/{login}")
    public void giveOperatorAccess(@PathVariable String login) {
        userService.giveOperatorPrivilege(login);
    }

}
