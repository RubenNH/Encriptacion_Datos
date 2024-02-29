package stay.cruz.cruzstay.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import stay.cruz.cruzstay.models.user.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthUser implements UserDetails {
    private String username;
    private String password;
    private boolean blocked;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthUser(String username, String password, boolean blocked, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.blocked = blocked;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public static AuthUser build(User user){
        Set<GrantedAuthority> authorities =
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet());
        return new AuthUser(
                user.getUsername(), user.getPassword(),
                user.getBlocked(), user.getStatus(), authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return blocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
