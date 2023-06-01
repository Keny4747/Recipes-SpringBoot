package recipes.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.model.UserEntity;
import recipes.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody UserEntity userEntity){
       return userService.addUser(userEntity);
    }
    @GetMapping("/all")
    public List<UserEntity> getAll(){
        return userService.getAll();
    }
}
