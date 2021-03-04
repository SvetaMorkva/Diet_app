package dao;

import entity.User_FoodType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_FoodTypeDAO extends DAOBase<User_FoodType> {
    public List<Long> findAllForUser(Long userId) {
        List<Long> foods = new ArrayList<>();;
        try {
            User_FoodType foodData = new User_FoodType();
            String queryString = "SELECT * FROM " + foodData.getTableName() + " WHERE User=?";
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, userId);
            var resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                foods.add(resultSet.getLong("FoodType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return foods;
    }

    public int removeUserFoodType(Long userId, Long foodId) {
        try {
            User_FoodType foodData = new User_FoodType();
            String queryString = "DELETE FROM " + foodData.getTableName() +
                    " WHERE User=? AND FoodType=?";
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, userId);
            ptmt.setLong(2, foodId);
            return ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return 0;
    }
}
