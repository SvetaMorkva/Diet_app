package com.kpi.iasa.dietapp.persistence.dao;

import com.kpi.iasa.dietapp.persistence.dao.entities.Ingestion;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;

import java.util.List;

public interface IIngestion extends IBase<Ingestion> {
    List<Ingestion> findAllByUser(User user);
}
