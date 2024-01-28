package com.example.hollidayCottages.service;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.Validator;
import com.example.hollidayCottages.contract.UserFullDto;
import com.example.hollidayCottages.model.Customer;
import com.example.hollidayCottages.model.User;
import com.example.hollidayCottages.model.UserType;
import com.example.hollidayCottages.repositories.ICatalogData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final ICatalogData data;
    public void registerUser(UserFullDto userFullDto) throws ExceptionWithMessage {
        try {
            userFullDto = new UserFullDto(Validator.validate(userFullDto.getFirstname()), Validator.validate(userFullDto.getLastname()),
                    Validator.validate(userFullDto.getPassword()), Validator.validate(userFullDto.getRePassword()),
                    Validator.validate(userFullDto.getEmail()), Validator.validate(userFullDto.getPhone()));
            if (!userFullDto.getEmail().matches("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"))
                throw new ExceptionWithMessage("Invalid email format");
            if (!userFullDto.getPassword().equals(userFullDto.getRePassword()))
                throw new ExceptionWithMessage("Passwords do not match");
            if (data.getUser().existsByEmail(userFullDto.getEmail()))
                throw new ExceptionWithMessage("E-mail is already taken");
            User user = new User(userFullDto.getEmail(), encodePassword(userFullDto.getPassword()), new UserType(1, "User"));
            data.getUser().save(user);
            data.getCustomer().save(new Customer(userFullDto.getFirstname(), userFullDto.getLastname(), userFullDto.getPhone(), false, user));
        } catch (Exception e) {
            throw new ExceptionWithMessage(e.getMessage());
        }
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
