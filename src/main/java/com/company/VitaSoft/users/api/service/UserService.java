package com.company.VitaSoft.users.api.service;

import com.company.VitaSoft.users.impl.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getUserList();

    public void giveOperatorPrivilege(String login);
}
