package martinez.ruben.api.repository;

import martinez.ruben.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u FROM User u WHERE u.username = :user")
    User findUserByName(@Param("user") String user);

    @Query("SELECT u FROM User u ORDER BY u.maxKills DESC")
    List<User> findTop3();

    @Query("SELECT u FROM User u ORDER BY u.maxKills DESC")
    List<User> findAllOrderByMaxKills();
}
