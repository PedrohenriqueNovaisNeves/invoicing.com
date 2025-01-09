package com.example.invoicing.com.controllers;

import com.example.invoicing.com.dtos.UserRecord;
import com.example.invoicing.com.models.UserModel;
import com.example.invoicing.com.repository.UserRepository;
import com.example.invoicing.com.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServices userServices;

    @GetMapping("/ping")
    public String ping(){
        return "ping!";
    }

    @PostMapping("/insertUser")
    public ResponseEntity<Object> insertUser(@RequestBody @Valid UserRecord userRecord){
        var user = new UserModel();
        BeanUtils.copyProperties(userRecord, user);
        if(userServices.isUsernameTaken(user)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Existing user " + user.getNameUser());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.saveUser(user));
    }

    @GetMapping("/listUsers")
    public List<UserModel> getAllUser(){
        return userServices.listUsers();
    }

    @GetMapping("/oneUser/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id")UUID id){
       return ResponseEntity.status(HttpStatus.OK).body(userServices.listOneUser(id));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id")UUID id, @RequestBody @Valid UserRecord userRecord){
        return ResponseEntity.status(HttpStatus.OK).body(userServices.updateUser(id, userRecord));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> user0 = userRepository.findById(id);

        if(user0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userRepository.delete(user0.get());
        return ResponseEntity.status(HttpStatus.OK).body("User delete successfully");
    }
}
