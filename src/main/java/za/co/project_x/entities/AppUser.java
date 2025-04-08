package za.co.project_x.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import za.co.project_x.entities.base.BaseEntity;

import java.util.Collection;

@Entity
@Table(name = "app_user")
public class AppUser extends BaseEntity implements UserDetails {

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "isAccountNonExpired", nullable = false)
    private boolean isAccountNonExpired;
    @Column(name = "isEnabled", nullable = false)
    private boolean isEnabled;
    @Column(name = "isAccountNonLocked", nullable = false)
    private boolean isAccountNonLocked;
    @Column(name = "isCredentialsNonExpired", nullable = false)
    private boolean isCredentialsNonExpired;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Spring Security's UserDetails methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Role is assumed to be a single role value, like 'USER', 'ADMIN'
        return AuthorityUtils.createAuthorityList("ROLE_" + this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired; // If you want to implement account expiration, modify accordingly
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked; // If you want to implement account locking, modify accordingly
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired; // If you want to implement password expiration, modify accordingly
    }

    @Override
    public boolean isEnabled() {
        return isEnabled; // Modify based on your requirements for account enabling/disabling
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }
}
