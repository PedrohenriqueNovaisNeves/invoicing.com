package com.example.invoicing.com.services;

import com.example.invoicing.com.dtos.UserRecord;
import com.example.invoicing.com.models.UserModel;
import com.example.invoicing.com.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

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
        return userRepository.save(userModel);
    }

    public boolean isUsernameTaken(UserModel userModel) {
        List<UserModel> users = userRepository.findAll();

        List<UserModel> duplicados = users.stream()
                .filter(user -> user.getNameUser().equalsIgnoreCase(userModel.getNameUser()))
                .collect(Collectors.toList());

        if (!duplicados.isEmpty()) {
            System.out.println("Usuários duplicados encontrados: " + duplicados);
            return true;
        }
        return false;
    }

    public ResponseEntity<Object> updateUser(UUID id, UserRecord userRecord){
        Optional<UserModel> user1 = userRepository.findById(id);

        if(user1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        var user2 = user1.get();
        BeanUtils.copyProperties(userRecord, user2);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user2));
    }

    public ResponseEntity<Object> deleteUser(UUID id){
        Optional<UserModel> user = userRepository.findById(id);

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }

        userRepository.delete(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("User delete successfully");
    }

    public ResponseEntity<Object> deleteAllUsers(){
        userRepository.deleteAll();

        return ResponseEntity.status(HttpStatus.OK).body("All Users delete successfully");
    }

    
}
