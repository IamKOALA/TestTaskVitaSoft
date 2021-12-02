package com.company.VitaSoft.users.impl.repository;

import com.company.VitaSoft.users.impl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);
}
