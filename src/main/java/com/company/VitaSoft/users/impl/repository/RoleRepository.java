package com.company.VitaSoft.users.impl.repository;

import com.company.VitaSoft.users.impl.enums.ERole;
import com.company.VitaSoft.users.impl.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
