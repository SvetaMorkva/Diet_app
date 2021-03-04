package dao;

import entity.FoodType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodTypeDAO extends DAOBase<FoodType> {
    public List<FoodType> findAll() {
        List<FoodType> foods = new ArrayList<>();;
        try {
            FoodType foodData = new FoodType();
            String queryString = "SELECT * FROM " + foodData.getTableName();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                FoodType food = new FoodType();
                food.parse(resultSet);
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return foods;
    }

    public FoodType findById(Long foodId) {
        try {
            FoodType entity = new FoodType();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE " + entity.getIdName() + "=?";
            System.out.println(queryString);
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, foodId);
            var resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                entity.parse(resultSet);
                return entity;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return null;
    }
}