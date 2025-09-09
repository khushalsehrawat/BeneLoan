package com.BeneLoan.BeneLoan.repository;

import com.BeneLoan.BeneLoan.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

}
