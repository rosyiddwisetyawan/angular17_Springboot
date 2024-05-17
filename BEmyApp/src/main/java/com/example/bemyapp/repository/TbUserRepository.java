package com.example.bemyapp.repository;

import com.example.bemyapp.model.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbUserRepository extends JpaRepository<TbUser, Integer> {
    TbUser findByUsername(String username);

    TbUser findByUsernameAndPassword(String username, String password);
}