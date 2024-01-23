package com.example.hollidayCottages.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "regular_customer", nullable = false)
    private boolean regularCustomer;

    @ManyToOne
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

    public Customer(String name, String surname, String phoneNumber, boolean regularCustomer, User user) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.regularCustomer = regularCustomer;
        this.user = user;
    }
}
