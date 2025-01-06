package com.example.invoicing.com.services;

import com.example.invoicing.com.models.UserModel;
import com.example.invoicing.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public List<UserModel> listUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<Object> listOneUser(UUID id){
        Optional<UserModel> user = userRepository.findById(id);

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + id + " not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    public UserModel saveUser(UserModel userModel){

        if(userRepository.findById(userModel.getIdUser()).isPresent()){
            throw new RuntimeException("Usuário já existe com o UUID: " + userModel.getIdUser());
        }

        return userRepository.save(userModel);
    }
}
