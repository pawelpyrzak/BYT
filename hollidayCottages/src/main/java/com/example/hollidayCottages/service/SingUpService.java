package com.example.hollidayCottages.service;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.Validator;
import com.example.hollidayCottages.contract.UserDto;
import com.example.hollidayCottages.model.Customer;
import com.example.hollidayCottages.model.User;
import com.example.hollidayCottages.model.UserType;
import com.example.hollidayCottages.repositories.ICatalogData;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SingUpService {
    private final ICatalogData data;

    public void registerUser(UserDto userDto) throws ExceptionWithMessage {

        if (data.getUser().existsByEmail(userDto.getEmail())) {
            throw new ExceptionWithMessage("E-mail is already taken");
        }

        try {
            userDto.setEmail(Validator.validate(userDto.getEmail()));
            userDto.setPhone(Validator.validate(userDto.getPhone()));
            userDto.setPassword(Validator.validate(userDto.getPassword()));
            userDto.setRePassword(Validator.validate(userDto.getRePassword()));
            userDto.setFirstname(Validator.validate(userDto.getFirstname()));
            userDto.setLastname(Validator.validate(userDto.getLastname()));
            System.out.println("validate");

            if (!userDto.getEmail().matches("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$")) {
                throw new ExceptionWithMessage("Invalid email format");
            }
            if (!userDto.getPassword().equals(userDto.getRePassword())) {
                throw new ExceptionWithMessage("Passwords do not match");
            }
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(encodePassword(userDto.getPassword()));
            user.setUserType(new UserType(1, "User"));
            data.getUser().save(user);

            Customer customer = new Customer();
            customer.setName(userDto.getFirstname());
            customer.setSurname(userDto.getLastname());
            customer.setPhoneNumber(userDto.getPhone());
            customer.setUser(user);
            data.getCustomer().save(customer);

        } catch (Exception e) {
            throw new ExceptionWithMessage(e.getMessage());
        }
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
