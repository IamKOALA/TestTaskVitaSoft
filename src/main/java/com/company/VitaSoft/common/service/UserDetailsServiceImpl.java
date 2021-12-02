package com.company.VitaSoft.common.service;

import com.company.VitaSoft.common.service.UserDetailsImpl;
import com.company.VitaSoft.users.impl.entity.User;
import com.company.VitaSoft.users.impl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);

        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        }
        else {
            throw new UsernameNotFoundException("No such user with login: " + login);
        }
    }
}
