package com.kpi.iasa.dietapp;

import com.kpi.iasa.dietapp.persistence.dao.daoimpl.UserImpl;
import com.kpi.iasa.dietapp.persistence.dao.entities.FoodType;
import com.kpi.iasa.dietapp.persistence.dao.entities.Ingestion;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import com.kpi.iasa.dietapp.service.FoodService;
import com.kpi.iasa.dietapp.service.IngestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IngestionServiceTest {
    @Autowired
    private FoodService foodService;
    @Autowired
    private IngestionService ingService;
    @Autowired
    private UserImpl userDao;

    @Test
    public void daoCreateIngection() {
        User user = null;
        Ingestion ing = null;
        List<FoodType> foods = new ArrayList<>();
        try {
            final var foodNum = 10;
            final var foodTemplateName = "Ice-cream";
            user = userDao.add(new User("Somebody", "cddcd"));
            for (int i = 0; i < foodNum; i++) {
                String testNum = "30";
                foods.add(foodService.addFoodType(foodTemplateName + i, testNum, testNum,
                        testNum, testNum, user.getLoginName()));
            }
            var foodsByUser = ingService.getUserFoodTypes(user.getLoginName());
            assertThat(foodsByUser, is(foods));
            List<Long> foodIds = new ArrayList<>();
            for (var food : foodsByUser) {
                foodIds.add(food.getId());
            }
            ing = ingService.addIngestion(new Timestamp(System.currentTimeMillis()),
                    foodIds, user.getLoginName());
            var foodsByIng = ingService.getIngestionFoodTypes(ing.getId());
            assertThat(foodsByIng, is(foods));
        } finally {
            if (ing != null) {
                ingService.removeIngestion(ing.getId());
            }
            if (!foods.isEmpty()) {
                for (var food : foods) {
                    foodService.removeUserFoodType(food.getId().toString());
                }
            }
            if (user != null) {
                userDao.delete(user);
            }
        }
        var foodsByUser = ingService.getUserFoodTypes(user.getLoginName());
        assert (foodsByUser == null);
        assert (userDao.findById(user.getId()).isEmpty());
    }
}
