package com.kpi.iasa.dietapp.persistence.dao.daoimpl;

import com.kpi.iasa.dietapp.persistence.dao.IBase;
import com.kpi.iasa.dietapp.persistence.dao.entities.Rights;
import com.kpi.iasa.dietapp.persistence.dao.repositories.RightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RightsImpl implements IBase<Rights> {
    private final RightsRepository repository;

    public Rights add(Rights entity) {
        return repository.save(entity);
    }

    public Rights update(Rights entity) {
        return repository.save(entity);
    }

    public void delete(Rights entity) {
        repository.delete(entity);
    }

    public List<Rights> findAll() {
        return repository.findAll();
    }

    public Optional<Rights> findById(Long id) {
        return repository.findById(id);
    }
}