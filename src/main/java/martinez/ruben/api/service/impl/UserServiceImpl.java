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

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User save(User user) {
        user.setPassword(cipher.hashCipher(user.getPassword()));
        User userInBd = this.userRepository.findUserByName(user.getUsername());
        if (userInBd == null) {
            user.setMaxWave(0);
            user.setPlaytime(0);
            user.setGames(0);
            user.setKills(0);
            user.setMaxKills(0);
            user.setDamage(0);
            user.setMaxDamage(0);
            return this.userRepository.save(user);
        }
        return null;
    }

    public Optional<User> findById(Integer id) {
        return this.userRepository.findById(id);
    }

    public User findByName(String username) {
        return this.userRepository.findUserByName(username);
    }

    public int updateUser(Integer id, User userGame) {
        User userInBd = this.userRepository.findById(id).orElse(null);

        if (userInBd != null) {
            if(userGame.getMaxWave() > userInBd.getMaxWave()){
                userInBd.setMaxWave(userGame.getMaxWave());
            }

            userInBd.setPlaytime(userInBd.getPlaytime() + userGame.getPlaytime());
            userInBd.setGames(userInBd.getGames()+1);
            userInBd.setKills(userInBd.getKills() + userGame.getKills());

            if(userGame.getKills() > userInBd.getMaxKills()){
                userInBd.setMaxKills(userGame.getKills());
            }

            userInBd.setDamage(userInBd.getDamage() + userGame.getDamage());

            if(userGame.getDamage() > userInBd.getMaxDamage()){
                userInBd.setMaxDamage(userGame.getDamage());
            }

            System.out.println("update ok");
            return 200;
        }

        System.out.println("update not ok");
        return 0;
    }

    @Override
    public User compare(User userTry) {
        User userInBd = this.userRepository.findUserByName(userTry.getUsername());
        userTry.setPassword(cipher.hashCipher(userTry.getPassword()));
        if (userInBd != null && userInBd.getPassword().equals(userTry.getPassword())) {
            System.out.println("User founded");
            return userInBd;
        }
        System.out.println("User not found");
        return null;
    }

    @Override
    public List<User> findAllOrderByMaxKills() {
        return this.userRepository.findAllOrderByMaxKills();
    }
}
