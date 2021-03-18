package com.kpi.iasa.dietapp.persistence.dao.repositories;

import com.kpi.iasa.dietapp.persistence.dao.entities.Ingestion;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngestionRepository extends JpaRepository<Ingestion, Long> {
    List<Ingestion> findAllByUser(User user);
}
