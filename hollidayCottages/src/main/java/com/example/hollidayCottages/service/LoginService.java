package com.example.hollidayCottages.service;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.Validator;
import com.example.hollidayCottages.model.Customer;
import com.example.hollidayCottages.model.User;
import com.example.hollidayCottages.repositories.ICatalogData;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final ICatalogData data;

    public User authenticateUser(String email, String password) throws ExceptionWithMessage {
        email = Validator.validate(email);
        password = Validator.validate(password);
        if (!email.matches("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new ExceptionWithMessage("Invalid email format");
        }
        Optional<User> user = data.getUser().findByEmail(email);
        if (user.isEmpty()) {
            throw new ExceptionWithMessage("User not found");
        }
        if (!new BCryptPasswordEncoder().matches(password, user.get().getPassword())) {
            throw new ExceptionWithMessage("User bad password");
        }

        return user.get();
    }

    public Optional<Customer> getCustomerById(int id) {
       return data.getCustomer().findCustomerByUserId(id);
    }

}
