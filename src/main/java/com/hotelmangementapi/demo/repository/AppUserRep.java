package com.hotelmangementapi.demo.repository;

import com.hotelmangementapi.demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AppUserRep extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByUsername(String username);
    Optional<AppUser> findAppUserByEmail(String email);
}
