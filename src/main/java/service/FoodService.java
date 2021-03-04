package service;

import com.mysql.jdbc.StringUtils;
import dao.DaoFactory;
import entity.FoodType;
import entity.User_FoodType;

import java.util.ArrayList;
import java.util.List;

public class FoodService {
    private static DaoFactory factory = DaoFactory.getInstance();

    public List<FoodType> getAll() {
        return factory.createFoodTypeDao().findAll();
    }

    public List<FoodType> getUserFoodType(Long userId) {
        List<FoodType> userFoods = new ArrayList<>();
        List<Long> userFoodIds = factory.createUserFoodTypeDao().findAllForUser(userId);
        for (var id: userFoodIds) {
            var food = factory.createFoodTypeDao().findById(id);
            userFoods.add(food);
        }
        return userFoods;
    }

    public void createFoodType(FoodType foodType, String login) {
        var foodId = factory.createFoodTypeDao().add(foodType);
        var user = factory.createUserDao().findByLogin(login);
        if (user != null) {
            factory.createUserFoodTypeDao().add(new User_FoodType(user.getUserId(), foodId));
        }
        System.out.println("user is null for " + login);
    }

    private Boolean isDataValid(List<String> data) {
        for (var str: data) {
            if (StringUtils.isNullOrEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public Boolean removeUserFoodType(Long userId, String foodId) {
        try {
            if (StringUtils.isNullOrEmpty(foodId)) {
                return false;
            }
            Long _foodId = Long.valueOf(foodId);
            return factory.createUserFoodTypeDao().removeUserFoodType(userId, _foodId) > 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }
}
