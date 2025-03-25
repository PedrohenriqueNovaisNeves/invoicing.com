package com.example.invoicing.com.services;

import com.example.invoicing.com.dtos.UserRecord;
import com.example.invoicing.com.models.UserModel;
import com.example.invoicing.com.repository.UserRepository;
import com.example.invoicing.com.validation.ValidationsUser;
import org.apache.coyote.Response;
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

    @Autowired
    ValidationsUser validationsUser;

    public UserModel saveUser(UserModel userModel){
        if(validationsUser.validateRegistration(userModel)){
            throw new RuntimeException("user already registered");
        }

        return userRepository.save(userModel);
    }

    public List<UserModel> listAllUsers(){
        return userRepository.findAll();
    }

    public Object listOneUser(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public UserModel updateUser(UUID id, UserModel userModel){
        Optional<UserModel> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }

        var newUser = user.get();

        newUser.setNameUser((userModel.getNameUser()));
        newUser.setCPF(userModel.getCPF());
        newUser.setRG(userModel.getRG());
        newUser.setEmail(userModel.getEmail());

        return userRepository.save(newUser);
    }

    public void deleteOneUser(UUID id){
        Optional<UserModel> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }

        userRepository.delete(user.get());
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
}
