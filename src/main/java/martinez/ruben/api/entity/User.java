package martinez.ruben.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String lastname;
    private String username;
    private String password;
    private String email;

    private Integer maxWave;
    private float playtime;
    private Integer games;
    private Integer kills;
    private Integer maxKills;
    private Integer damage;
    private Integer maxDamage;
}
