package recipes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import recipes.model.UserEntity;
import recipes.repository.UserRepository;

import java.util.Optional;

@Component
public class UserEntityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity= repository.findByEmailIgnoreCase(username);
        return userEntity.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found "+username));
    }
}
