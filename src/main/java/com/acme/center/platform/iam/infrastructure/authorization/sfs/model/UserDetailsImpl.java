package com.acme.center.platform.iam.infrastructure.authorization.sfs.model;

import com.acme.center.platform.iam.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * User details implementation
 */
@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor
     * @param username Username
     * @param password Password
     * @param authorities Authorities
     */
    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    /**
     * Build user details
     * @param user User
     * @return User details
     */
    public static UserDetailsImpl build(User user) {
        var authorities = user.getRoles().stream()
                .map(role -> role.getName().name()).map(SimpleGrantedAuthority::new).toList();
        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
