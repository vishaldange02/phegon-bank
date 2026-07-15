package com.phegon.phegonbank.auth_users.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.phegon.phegonbank.account.entity.Account;
import com.phegon.phegonbank.role.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Email
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email is required")
    private String email;

    private String password;
    private String profilePictureUrl;
    private boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "users_roles",  //@JoinTable is used to define a table that connects two entities in a relationship.
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
                )
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;



}
