package com.shopping.repository;

import org.springframework.data.jpa.repository.*;

import com.shopping.model.UserDetails;

import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository <UserDetails,Long>{

    public Optional<UserDetails> findByUserId(Long userId);

    public Optional<UserDetails> findByFirstNameAndLastName(String firstName,String lastName);
}
