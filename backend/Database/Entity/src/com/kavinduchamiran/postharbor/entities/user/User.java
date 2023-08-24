package com.kavinduchamiran.postharbor.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter @Setter @ToString @EqualsAndHashCode
public class User {
    @Id
    @NotEmpty
    String id;

    @NotBlank
    @Size(min = 1, max = 20)
    String username;

    @NotBlank
    @Size(min = 1, max = 20)
    String firstName;

    @NotBlank
    @Size(min = 1, max = 20)
    String lastName;

    @NotBlank
    @Email
    @Size(min = 1, max = 50)
    String email;

    @NotBlank
    @Transient
    String password;

    @NotBlank
    String hashedPassword;

    @URL
    String profilePicture;

    @NotNull
    Date createdAt = new Date();

    @NotNull
    Date updatedAt = new Date();

}
