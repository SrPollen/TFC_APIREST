package martinez.ruben.api.controller;

import martinez.ruben.api.entity.User;
import martinez.ruben.api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/user")
    public List<User> index(){
        return userServiceImpl.findAll();
    }

    @PostMapping("/user")
    public User register(@RequestBody User user){
        return userServiceImpl.save(user);
    }

    @PostMapping("/user/login")
    public boolean login(@RequestBody User user){
        return userServiceImpl.compare(user);
    }
}
