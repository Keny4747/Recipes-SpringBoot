package recipes.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.model.UserEntity;
import recipes.repository.UserRepository;

import java.util.List;

@Service

public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService (UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<HttpStatus> addUser(UserEntity userEntity){

        if(userRepository.findByEmailIgnoreCase(userEntity.getEmail()).isEmpty()){
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userRepository.save(userEntity);
            return ResponseEntity.status(HttpStatus.OK).build();

        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }
}
