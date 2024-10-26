package org.example.lab4.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}

