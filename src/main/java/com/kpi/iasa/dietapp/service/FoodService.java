package com.kpi.iasa.dietapp.service;

import com.kpi.iasa.dietapp.persistence.dao.daoimpl.FoodTypeImpl;
import com.kpi.iasa.dietapp.persistence.dao.daoimpl.UserImpl;
import com.kpi.iasa.dietapp.persistence.dao.entities.FoodType;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodTypeImpl foodDao;
    private final UserImpl userDao;

    private FoodType createFood(String name, String protein, String fats,
                                String carbs, String calories, User user) {
        try {
            Integer _protein = Integer.valueOf(protein);
            Integer _fats = Integer.valueOf(fats);
            Integer _carbs = Integer.valueOf(carbs);
            Integer _calories = Integer.valueOf(calories);
            if (_protein < 0 || _fats < 0 || _carbs < 0 || _calories < 0) {
                return null;
            }
            return new FoodType(null, name, _protein, _fats, _carbs, _calories, user);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FoodType> getAll() {
        return foodDao.findAll();
    }

    public List<FoodType> getUserFoodType(Long userId) {
        var user = userDao.findById(userId);
        return user.isEmpty() ? null : foodDao.findAllByUser(user.get());
    }

    public FoodType addFoodType(String name, String protein, String fats,
                            String carbs, String calories, String login) {
        List<String> data = Arrays.asList(name, protein, fats, carbs, calories, login);
        if (!isDataValid(data)) {
            System.out.println("data not valid!");
            return null;
        }
        var user = userDao.findByLogin(login);
        var foodType = createFood(name, protein, fats, carbs, calories, user);
        if (user != null && foodType != null) {
            return foodDao.add(foodType);
        }
        return null;
    }

    private Boolean isDataValid(List<String> data) {
        for (var str: data) {
            if (StringUtils.isNullOrEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public Boolean removeUserFoodType(String foodId) {
        try {
            if (StringUtils.isNullOrEmpty(foodId)) {
                return false;
            }
            Long _foodId = Long.valueOf(foodId);
            var food = foodDao.findById(_foodId);
            if (food.isPresent()) {
                foodDao.delete(food.get());
                return true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }
}