package com.kpi.iasa.dietapp.persistence.dao.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique=true)
    private String loginName;

    private String passwordHash;
    private Integer weight;
    private Integer height;
    private Integer rightsId;

    @OneToMany
    private List<FoodType> userFoodTypes;

    @OneToMany
    private List<Ingestion> userIngestions;

    public User(String login, String pass) {
        loginName = login;
        passwordHash = pass;
    }

    public User(String login, String pass, Integer _weight, Integer _height) {
        loginName = login;
        passwordHash = pass;
        weight = _weight;
        height = _height;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof User)) {
            return false;
        }

        User user = (User) object;
        return user.loginName.equals(loginName) &&
                user.passwordHash.equals(passwordHash);
    }
}
