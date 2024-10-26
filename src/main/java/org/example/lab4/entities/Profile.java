package org.example.lab4.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    private String address;
    private String dateOfBirth;

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;
}

