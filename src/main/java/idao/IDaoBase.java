package idao;

import entity.EntityBase;

import java.util.List;

public interface IDaoBase<T extends EntityBase> {
    void add(T t);
    void update(T t);
    void delete(T t);

    List<T> findAll();
    T findById(Long id);
}