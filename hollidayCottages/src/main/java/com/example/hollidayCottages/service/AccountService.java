package com.example.hollidayCottages;

import com.example.hollidayCottages.Exceptions.EmailAlreadyExistsException;
import com.example.hollidayCottages.Exceptions.InvalidUserDataException;
import com.example.hollidayCottages.Exceptions.RegistrationException;
import com.example.hollidayCottages.model.Customer;
import com.example.hollidayCottages.model.User;
import com.example.hollidayCottages.model.UserType;
import com.example.hollidayCottages.repositories.ICatalogData;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class AccountService {
    private final ICatalogData data;

    public void registerUser(String firstname, String lastname, String password, String email, String phone) throws EmailAlreadyExistsException, RegistrationException {

        if (data.getUser().existsByEmail(email)) {
            throw new EmailAlreadyExistsException("E-mail is already taken");
        }

        try {
            User user = new User();
            user.setEmail(email);
            user.setPassword(encodePassword(password));
            user.setUserType(new UserType(1,"User")); // Assuming UserType with ID 1 corresponds to regular users
            data.getUser().save(user);

            Customer customer = new Customer();
            customer.setName(firstname);
            customer.setSurname(lastname);
            customer.setPhoneNumber(phone);
            customer.setUser(user);
            data.getCustomer().save(customer);

        } catch (Exception e) {
            throw new RegistrationException("Error during registration", e);
        }
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
    private void validateUserData(String firstname, String lastname, String password, String rePassword, String email, String phone) throws InvalidUserDataException {
        if (StringUtils.isEmpty(firstname) || StringUtils.isEmpty(lastname) || StringUtils.isEmpty(email) || StringUtils.isEmpty(password) || StringUtils.isEmpty(rePassword)) {
            throw new InvalidUserDataException("Please fill in all the required fields");
        }

        if (!email.matches("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new InvalidUserDataException("Invalid email format");
        }

        if (!password.equals(rePassword)) {
            throw new InvalidUserDataException("Passwords do not match");
        }
    }

}
