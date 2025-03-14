package com.example.invoicing.com.validation;

import com.example.invoicing.com.dtos.UserRecord;
import com.example.invoicing.com.models.UserModel;
import com.example.invoicing.com.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidationsUser {

    @Autowired
    UserRepository userRepository;

    public boolean validationCpfUser(String cpfUser){

        char[] convertStringToChar = cpfUser.toCharArray();
        int numberOfCharacters = convertStringToChar.length;

        if(numberOfCharacters != 11){
            return true;
        }

        return false;
    }

    public boolean validateRegistration(UserModel userModel) {
        List<UserModel> users = userRepository.findAll();

        List<UserModel> duplicados = users.stream()
                .filter(user -> user.getCPF().equalsIgnoreCase(userModel.getCPF()))
                .collect(Collectors.toList());

        if (!duplicados.isEmpty()) {
            System.out.println("Usuários duplicados encontrados: " + duplicados);
            return true;
        }
        return false;
    }


}