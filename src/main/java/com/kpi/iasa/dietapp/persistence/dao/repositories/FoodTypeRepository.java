package com.kpi.iasa.dietapp.persistence.dao.repositories;

import com.kpi.iasa.dietapp.persistence.dao.entities.FoodType;
import com.kpi.iasa.dietapp.persistence.dao.entities.Ingestion;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {
    List<FoodType> findAllByUser(User user);
    List<FoodType> findAllByIngestions(Ingestion ingestion);
}