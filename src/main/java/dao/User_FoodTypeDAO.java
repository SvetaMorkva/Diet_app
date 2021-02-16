package dao;

import entity.EntityBase;
import entity.User_FoodType;
import idao.IDaoBase;

import java.sql.Connection;
import java.util.List;

public class User_FoodTypeDAO implements IDaoBase<User_FoodType> {
    DAOBase<User_FoodType> daoBase;

    public User_FoodTypeDAO(Connection _connection) {
        daoBase = new DAOBase<>(_connection);
    }

    public void add(User_FoodType user_foodType) {
        daoBase.add(user_foodType);
    }

    public void update(User_FoodType user) {
        daoBase.update(user);
    }

    public void delete(User_FoodType user) {
        daoBase.delete(user);
    }

    public List<User_FoodType> findAll() {
        return daoBase.findAll();
    }

    public User_FoodType findById(Long user_foodTypeId) {
        return (User_FoodType)daoBase.findById(user_foodTypeId);
    }
}
