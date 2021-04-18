package martinez.ruben.api.controller;

import martinez.ruben.api.entity.User;
import martinez.ruben.api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @CrossOrigin
    @GetMapping("/user")
    public List<User> index(){
        return userServiceImpl.findAll();
    }

    @CrossOrigin
    @GetMapping("/user/{id}")
    public Optional<User> show(@PathVariable Integer id){
        return userServiceImpl.findById(id);
    }

    @CrossOrigin
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userServiceImpl.save(user);
    }

    @CrossOrigin
    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userServiceImpl.compare(user);
    }
}
