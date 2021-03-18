package com.kpi.iasa.dietapp.persistence.dao.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Ingestion implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Timestamp eatingOn;

    @JoinColumn
    @ManyToOne
    @NotNull
    private User user;

    @ManyToMany
    @JoinTable(name = "jn_food_type_ingestion",
        joinColumns = @JoinColumn(name = "ing_fk"),
        inverseJoinColumns = @JoinColumn(name = "food_fk"))
    private List<FoodType> ingestionFoodTypes;

    public Ingestion(Long _id, Timestamp _date, User _user, List<FoodType> foodTypes) {
        id = _id;
        eatingOn = _date;
        user = _user;
        ingestionFoodTypes = foodTypes;
    }
}
