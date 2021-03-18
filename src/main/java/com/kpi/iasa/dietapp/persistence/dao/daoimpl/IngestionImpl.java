package com.kpi.iasa.dietapp.persistence.dao.daoimpl;

import com.kpi.iasa.dietapp.persistence.dao.IIngestion;
import com.kpi.iasa.dietapp.persistence.dao.entities.Ingestion;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import com.kpi.iasa.dietapp.persistence.dao.repositories.IngestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IngestionImpl implements IIngestion {
    private final IngestionRepository repository;

    public Ingestion add(Ingestion entity) {
        return repository.save(entity);
    }

    public Ingestion update(Ingestion entity) {
        return repository.save(entity);
    }

    public void delete(Ingestion entity) {
        repository.delete(entity);
    }

    public List<Ingestion> findAll() {
        return repository.findAll();
    }

    public Optional<Ingestion> findById(Long id) {
        return repository.findById(id);
    }

    public List<Ingestion> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

}
