package com.example.hollidayCottages.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    public User(String email, String password, UserType userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
}
