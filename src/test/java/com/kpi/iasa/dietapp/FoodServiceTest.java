package com.kpi.iasa.dietapp;

import com.kpi.iasa.dietapp.persistence.dao.daoimpl.UserImpl;
import com.kpi.iasa.dietapp.persistence.dao.entities.FoodType;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import com.kpi.iasa.dietapp.service.FoodService;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FoodServiceTest {
    @Autowired
    private FoodService service;
    @Autowired
    private UserImpl userDao;

    @Test
    public void daoGetUserFoodTypes() {
        User user = null;
        List<FoodType> foods = new ArrayList<>();
        try {
            final var foodNum = 10;
            final var foodTemplateName = "Ice-cream";
            user = userDao.add(new User("Somebody", "cddcd"));
            for (int i = 0; i < foodNum; i++) {
                String testNum = "30";
                foods.add(service.addFoodType(foodTemplateName + i, testNum, testNum,
                        testNum, testNum, user.getLoginName()));
            }
            var foodsByUser = service.getUserFoodType(user.getId());
            assertThat(foodsByUser, is(foods));
        } finally {
            if (!foods.isEmpty()) {
                for (var food : foods) {
                    service.removeUserFoodType(food.getId().toString());
                }
            }
            if (user != null) {
                userDao.delete(user);
            }
        }
        assert (userDao.findById(user.getId()).isEmpty());
    }
}
