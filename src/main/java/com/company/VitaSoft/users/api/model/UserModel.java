package com.company.VitaSoft.users.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserModel {
    Long id;
    String login;

    @JsonIgnore
    String password;
}
