package com.kpi.iasa.dietapp;

import com.kpi.iasa.dietapp.persistence.dao.daoimpl.FoodTypeImpl;
import com.kpi.iasa.dietapp.persistence.dao.entities.FoodType;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodTypeTest {
    @Autowired
    private FoodTypeImpl foodDao;
    private FoodType testFood = null;
    private final String testName = "Salo";

    private void updateName(String name) {
        testFood.setName(name);
        var foodType = foodDao.update(testFood);
        assertThat(foodType, notNullValue());
        assertThat(foodType.getName(), is(name));
        assertThat(foodType, is(testFood));
    }

    private FoodType createDefaultTestFood(String name, Long id) {
        Integer testNum = 30;
        return new FoodType(id, name, testNum, testNum, testNum, testNum, null);
    }

    @BeforeAll
    void setup() {
        assertThat(foodDao, notNullValue());
        testFood = foodDao.add(createDefaultTestFood(testName, null));
        assertThat(testFood, notNullValue());
    }

    @Test
    public void daoAdd() {
        assertThat(testFood, is(createDefaultTestFood(testName, testFood.getId())));
    }

    @Test
    public void daoUpdate() {
        updateName(testName + "2");
        updateName(testName);
    }

    @Test
    public void daoFindAll() {
        FoodType foodType = null;
        var foodslistBeforeAdd = foodDao.findAll().size();
        try {
            foodType = foodDao.add(createDefaultTestFood("Ice-cream", null));
            assertThat(foodType, notNullValue());
            var foodslistAfterAdd = foodDao.findAll().size();
            assert(foodslistBeforeAdd + 1 == foodslistAfterAdd);
        } finally {
            if (foodType != null) {
                foodDao.delete(foodType);
            }
        }
        var foodslistAfterDelete = foodDao.findAll().size();
        assert(foodslistBeforeAdd == foodslistAfterDelete);
    }

    @Test
    public void daoDelete() {
        FoodType foodType = null;
        try {
            foodType = foodDao.add(createDefaultTestFood("Ice-cream", null));
            assertThat(foodType, notNullValue());
        } finally {
            if (foodType != null) {
                foodDao.delete(foodType);
            }
        }
        var nullUser = foodDao.findById(foodType.getId());
        assert(nullUser.isEmpty());
    }

    @AfterAll
    void close() {
        foodDao.delete(testFood);
    }
}