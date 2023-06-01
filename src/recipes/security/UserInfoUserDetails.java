package recipes.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import recipes.model.UserEntity;

import java.util.Collection;
import java.util.Collections;

public class UserInfoUserDetails implements UserDetails {
    private String email;
    private String password;

    public UserInfoUserDetails(UserEntity userEntity){
        this.email =userEntity.getEmail();
        this.password= userEntity.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
