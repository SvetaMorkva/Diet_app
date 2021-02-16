package dao;

import entity.ConnectionFactory;
import entity.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDAOTest {
    Connection connection = null;
    UserDAO userDao = null;
    User user = null;

    @BeforeAll
    void setup() throws SQLException {
        connection = ConnectionFactory.getInstance().getConnection();

        user = new User("Somebody", "Sth");
        userDao = new UserDAO(connection);
        userDao.add(user);
    }

    @Test
    void add() {
        var userCheck = userDao.findByLogin("Somebody");
        assert(user.getPasswordHash().equals(userCheck.getPasswordHash()));
    }

    @Test
    void update() {
//        var updateUser = new User("Smbd", "Sth");
//        userDao.update(updateUser);
//        var userCheck = userDao.findByLogin("Smbd");
//        assert(userCheck.getLogin() == "Smbd");
//        userDao.update(user);
//        userCheck = userDao.findByLogin("Somebody");
//        assert(userCheck.getLogin() == "Somebody");
    }

    @Test
    void findAll() {
        var users = userDao.findAll();
        var previousSize = users.size();
        var newUser = new User("Somebody2", "Sth");
        userDao.add(newUser);
        users = userDao.findAll();
        assert(previousSize == (users.size() - 1));
        newUser = userDao.findByLogin("Somebody2");
        userDao.delete(newUser);
        users = userDao.findAll();
        assert(previousSize == users.size());
    }

    @AfterAll
    void close() throws SQLException {
        var user = userDao.findByLogin("Somebody");
        userDao.delete(user);
        if (connection != null) {
            connection.close();
        }
    }
}