package com.example.invoicing.com.controllers;

import com.example.invoicing.com.dtos.EmailRecord;
import com.example.invoicing.com.dtos.UserRecord;
import com.example.invoicing.com.models.UserModel;
import com.example.invoicing.com.services.EmailService;
import com.example.invoicing.com.services.UserServices;
import com.example.invoicing.com.validation.ValidationsUser;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @Autowired
    ValidationsUser validationsUser;

    @Autowired
    EmailService emailService;

    @GetMapping("/ping")
    public String ping(){
        return "ping!";
    }

    @PostMapping("/insertUser")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserRecord userRecord){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecord, userModel);

        userServices.saveUser(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered User");
    }

    @GetMapping("/listUsers")
    public ResponseEntity<List<UserModel>> listAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userServices.listAllUsers());
    }

    @GetMapping("/oneUser/{id}")
    public ResponseEntity<Object> listOneUser(@PathVariable(value = "id")UUID id){
        var user = userServices.listOneUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id")UUID id, @Valid @RequestBody UserRecord userRecord){
        var user = new UserModel();
        BeanUtils.copyProperties(userRecord, user);

        userServices.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("User update completed successfully");
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteOneUser(@PathVariable(value = "id")UUID id){
        userServices.deleteOneUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete user completed successfully");
    }

    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<Object> deleteAllUsers(){
        userServices.deleteAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Users");
    }

    @PostMapping("/sendMailUser")
    public ResponseEntity<String> sendMail(@RequestBody EmailRecord emailRecord){
        emailService.enviarEmailSimples(emailRecord.getPara(), emailRecord.getAssunto(), emailRecord.getCorpo());
        return ResponseEntity.status(HttpStatus.OK).body("Email send with successfully");
    }
}
