package com.kpi.iasa.dietapp.service;

import com.kpi.iasa.dietapp.persistence.dao.daoimpl.FoodTypeImpl;
import com.kpi.iasa.dietapp.persistence.dao.daoimpl.IngestionImpl;
import com.kpi.iasa.dietapp.persistence.dao.daoimpl.UserImpl;
import com.kpi.iasa.dietapp.persistence.dao.entities.FoodType;
import com.kpi.iasa.dietapp.persistence.dao.entities.Ingestion;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngestionService {
    private final FoodTypeImpl foodDao;
    private final UserImpl userDao;
    private final IngestionImpl ingDao;

    private Ingestion createIngestion(Timestamp date, User user, List<FoodType> foodTypes) {
        try {
            return new Ingestion(null, date, user, foodTypes);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FoodType> getUserFoodTypes(String login) {
        var user = userDao.findByLogin(login);
        return foodDao.findAllByUser(user);
    }

    public Ingestion addIngestion(Timestamp date, List<Long> foodIds, String login) {
        List<String> data = Arrays.asList(login);
        if (!isDataValid(data)) {
            System.out.println("data not valid!");
            return null;
        }
        var user = userDao.findByLogin(login);
        List<FoodType> foodTypes = new ArrayList<>();
        for (var id : foodIds) {
            var food = foodDao.findById(id);
            food.ifPresent(foodTypes::add);
        }
        var ingestion = createIngestion(date, user, foodTypes);
        if (user != null && ingestion != null) {
            return ingDao.add(ingestion);
        }
        return null;
    }

    public List<FoodType> getIngestionFoodTypes(Long id) {
        var ing = ingDao.findById(id);
        if (ing.isEmpty()) {
            return null;
        }
        return foodDao.findAllByIngestions(ing.get());
    }


    private Boolean isDataValid(List<String> data) {
        for (var str: data) {
            if (StringUtils.isNullOrEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public Boolean removeIngestion(Long id) {
        try {
            var ing = ingDao.findById(id);
            if (ing.isPresent()) {
                ingDao.delete(ing.get());
                return true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }
}
