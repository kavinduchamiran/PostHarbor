package com.kavinduchamiran.postharbor.entities.user;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kavinduchamiran.postharbor.entities.privilege.Privilege;
import com.kavinduchamiran.postharbor.entities.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIncludeProperties({"id", "username", "firstName", "lastName", "email", "profilePicture", "isEnabled", "createdAt", "roles"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1,
          max = 20)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(min = 1,
          max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 1,
          max = 20)
    private String lastName;

    @NotBlank
    @Email
    @Size(min = 1,
          max = 50)
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @URL
    private String profilePicture;

    private boolean isEnabled;

    @NotNull
    @Builder.Default
    private Date createdAt = new Date();

    @NotNull
    @Builder.Default
    private Date updatedAt = new Date();

    @ManyToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
               joinColumns = @JoinColumn(name = "user_id",
                                         referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "role_id",
                                                referencedColumnName = "id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    private Set<Role> roles;

    // from UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getPrivileges(roles);
    }

    // mapping both roles and privileges to GrantedAuthority objects
    private Collection<GrantedAuthority> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }

        return getGrantedAuthorities(privileges);
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
