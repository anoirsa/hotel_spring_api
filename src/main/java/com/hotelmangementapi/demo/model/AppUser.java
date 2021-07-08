package com.hotelmangementapi.demo.model;

import com.hotelmangementapi.demo.model.enums.AppUserRole;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Set;

@Setter
@ToString
@NoArgsConstructor
@Entity(
        name = "AppUser"
)
@Table(
        name = "appusers",
        uniqueConstraints = @UniqueConstraint(name = "email_user_unique",columnNames = {"username","email"})
)
public class AppUser implements UserDetails {

    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )

    @Id
    private Long id;
    private String username;
    private String password;
    @Email(message = "Email not valid")
    @Column(
            name = "email"
    )
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private boolean isEnabled;
    private boolean isCredentialsNonExpired;
    private boolean isAccountNonLocked;
    private boolean isAccountNonExpired;

    public AppUser(String username, String password, String email,AppUserRole appUserRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.appUserRole = appUserRole;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return appUserRole.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return this.email;
    }
    public AppUserRole getAppUserRole() {return this.appUserRole;}

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
