package com.kpi.iasa.dietapp.persistence.dao;

import com.kpi.iasa.dietapp.persistence.dao.entities.FoodType;
import com.kpi.iasa.dietapp.persistence.dao.entities.Ingestion;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;

import java.util.List;

public interface IFoodType extends IBase<FoodType> {
    List<FoodType> findAllByUser(User user);
    List<FoodType> findAllByIngestions(Ingestion ingestion);
}
