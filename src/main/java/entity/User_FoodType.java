package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User_FoodType extends EntityBase {
    Long User;
    Long FoodType;
    Long Id;

    public User_FoodType(Long id, Long user, Long foodType) {
        Id = id;
        User = user;
        FoodType = foodType;
    }
}