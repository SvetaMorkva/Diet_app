package com.kpi.iasa.dietapp.persistence.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class FoodType implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer protein;
    private Integer fats;
    private Integer carbs;
    private Integer calories;

    @JoinColumn
    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "ingestionFoodTypes")
    private List<Ingestion> ingestions;

    public FoodType(Long _id, String _name, Integer _protein, Integer _fats,
                    Integer _carbs, Integer _calories, User _user) {
        id = _id;
        name = _name;
        protein = _protein;
        fats = _fats;
        carbs = _carbs;
        calories = _calories;
        user = _user;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof FoodType)) {
            return false;
        }

        FoodType foodType = (FoodType) object;
        return foodType.getId().equals(id);
    }
}
