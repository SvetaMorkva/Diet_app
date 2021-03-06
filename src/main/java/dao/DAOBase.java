package dao;

import entity.ConnectionFactory;
import entity.EntityBase;
import idao.IDaoBase;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
public class DAOBase<T extends EntityBase> implements IDaoBase<T> {
    Connection connection;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public DAOBase() {
        try {
            connection = ConnectionFactory.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long add(T t) {
        try {
            String queryString = t.getInsertQuery();
            ptmt = connection.prepareStatement(queryString);
            t.serialize(ptmt, false);
            ptmt.executeUpdate();
            queryString = "SELECT LAST_INSERT_ID();";
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            System.out.println("Element Added Successfully in Table " + t.getTableName());
            if (resultSet.next()) {
                return resultSet.getLong("LAST_INSERT_ID()");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return 0L;
    }

    @Override
    public void update(T t) {
        try {
            String queryString = t.getUpdateQuery();
            ptmt = connection.prepareStatement(queryString);
            t.serialize(ptmt, true);
            ptmt.executeUpdate();
            System.out.println(queryString);
            System.out.println("Table " + t.getTableName() + " Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
    }

    @Override
    public void delete(T t) {
        try {
            String queryString = "DELETE FROM " + t.getTableName() +
                    " WHERE " + t.getIdName() + "=?";
            System.out.println(queryString);
            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, t.getId());
            ptmt.executeUpdate();
            System.out.println("Data deleted Successfully from table " + t.getTableName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cleanUp();
        }
    }
    @Override
    public List<T> findAll() { return null; };

    @Override
    public T findById(Long id) { return null; };

    public void cleanUp() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ptmt != null) {
                ptmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
