package com.company.VitaSoft.users.impl.service;

import com.company.VitaSoft.users.api.service.UserService;
import com.company.VitaSoft.users.impl.entity.Role;
import com.company.VitaSoft.users.impl.entity.User;
import com.company.VitaSoft.users.impl.enums.ERole;
import com.company.VitaSoft.users.impl.repository.RoleRepository;
import com.company.VitaSoft.users.impl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No user with such login")
class UserNotFoundException extends RuntimeException {
}

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void giveOperatorPrivilege(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        Optional<Role> operatorRole = roleRepository.findByName(ERole.OPERATOR);
        if (user.isPresent() && operatorRole.isPresent()) {
            if (user.get().getRoles().contains(operatorRole.get())) {
                return;
            }
            user.get().addRole(operatorRole.get());
            userRepository.save(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }
}
