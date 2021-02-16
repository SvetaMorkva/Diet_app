package dao;

import entity.EntityBase;
import entity.FoodType;
import idao.IDaoBase;

import java.sql.Connection;
import java.util.List;

public class FoodTypeDAO implements IDaoBase<FoodType> {
    DAOBase<FoodType> daoBase;

    public FoodTypeDAO(Connection _connection) {
        daoBase = new DAOBase<>(_connection);
    }

    public void add(FoodType type) {
        daoBase.add(type);
    }

    public void update(FoodType type) {
        daoBase.update(type);
    }

    public void delete(FoodType type) {
        daoBase.delete(type);
    }

    public List<FoodType> findAll() {
        return daoBase.findAll();
    }

    public FoodType findById(Long typeId) {
        return daoBase.findById(typeId);
    }
}