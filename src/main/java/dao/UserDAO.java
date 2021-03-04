package dao;

import entity.User;
import idao.IDaoUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAOBase<User> implements IDaoUser {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();;
        try {
            User userData = new User();
            String queryString = "SELECT * FROM " + userData.getTableName();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.parse(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return users;
    }

    public User findById(Long userId) {
        try {
            User entity = new User();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE " + entity.getIdName() + "=?";
            System.out.println(queryString);
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, userId);
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

    public User findByLogin(String login) {
        try {
            User entity = new User();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE " + "LoginName=?";
            System.out.println(queryString);
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, login);
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