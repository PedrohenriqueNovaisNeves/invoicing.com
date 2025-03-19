package com.example.invoicing.com.repository;

import com.example.invoicing.com.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    //Optional<UserModel> findById(UUID id);

    Optional<UserModel> findByNameUser( String nameUser);
}
