package com.kpi.iasa.dietapp.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface IBase<T> {
    T add(T t);
    T update(T t);
    void delete(T t);

    List<T> findAll();
    Optional<T> findById(Long id);
}
