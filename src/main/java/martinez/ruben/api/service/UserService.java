package martinez.ruben.api.service;

import martinez.ruben.api.entity.User;

import java.util.List;

public interface UserService  {
    User compare(User userTry);

    List<User> findAllOrderByMaxKills();
}
