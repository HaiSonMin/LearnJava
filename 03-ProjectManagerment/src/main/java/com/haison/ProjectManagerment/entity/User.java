package com.haison.ProjectManagerment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name" , nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    // 1 User have a lot of authorities and "Contrary"
    // It's an intermediate table linking "users table" and "roles table"
    // FetchType.EAGER: Everytime we load user we must load the role
    // CascadeType.ALL:
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), // Reference table users
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // Reference table roles
    )
    private Set<Role> roles;


}
