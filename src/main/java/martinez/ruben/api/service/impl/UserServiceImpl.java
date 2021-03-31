package martinez.ruben.api.service.impl;

import martinez.ruben.api.cipher.Cipher;
import martinez.ruben.api.entity.User;
import martinez.ruben.api.repository.UserRepository;
import martinez.ruben.api.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    Cipher cipher = new Cipher();
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User save(User user){
        user.setPassword(cipher.hashCipher(user.getPassword()));
        return this.userRepository.save(user);
    }

    public Optional<User> findById(Integer id){
        return this.userRepository.findById(id);
    }

    public User findByName(String username){
        return this.userRepository.findUserByName(username);
    }

    @Override
    public boolean compare(User userTry) {
        //User userInBd = this.userRepository.findById(userTry.getId()).orElse(null);
        User userInBd = this.userRepository.findUserByName(userTry.getUsername());
        userTry.setPassword(cipher.hashCipher(userTry.getPassword()));
        if(userInBd != null && userInBd.getPassword().equals(userTry.getPassword())){
            return true;
        }

        return false;
    }
}
