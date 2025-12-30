package com.mfsys.verto.repository;

import com.mfsys.verto.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    // Find a user by email
    Optional<UserModel> findByEmail(String email);
}
