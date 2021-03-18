package com.kpi.iasa.dietapp.persistence.dao.daoimpl;

import com.kpi.iasa.dietapp.persistence.dao.IFoodType;
import com.kpi.iasa.dietapp.persistence.dao.entities.FoodType;
import com.kpi.iasa.dietapp.persistence.dao.entities.Ingestion;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import com.kpi.iasa.dietapp.persistence.dao.repositories.FoodTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FoodTypeImpl implements IFoodType {
    private final FoodTypeRepository repository;

    public FoodType add(FoodType entity) {
        return repository.save(entity);
    }

    public FoodType update(FoodType entity) {
        return repository.save(entity);
    }

    public void delete(FoodType entity) {
        repository.delete(entity);
    }

    public List<FoodType> findAll() {
        return repository.findAll();
    }

    public Optional<FoodType> findById(Long id) {
        return repository.findById(id);
    }

    public List<FoodType> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    public List<FoodType> findAllByIngestions(Ingestion ingestion) {
        return repository.findAllByIngestions(ingestion);
    }
}