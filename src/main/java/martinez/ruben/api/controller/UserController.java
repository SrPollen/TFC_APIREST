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
    @GetMapping("/user/{id}")
    public Optional<User> show(@PathVariable Integer id){
        return userServiceImpl.findById(id);
    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public int update(@PathVariable Integer id, @RequestBody User user){
        return userServiceImpl.updateUser(id, user);
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

    @CrossOrigin
    @PostMapping("/gamelogin")
    public int gameLogin(@RequestBody User user){
        User userBD = userServiceImpl.compare(user);
        if(userBD != null){
            return userBD.getId();
        }
        return -1;
    }

    @CrossOrigin
    @GetMapping("/topusers")
    public List<User> getUsersByMaxKills(){
        return userServiceImpl.findAllOrderByMaxKills();
    }


}
