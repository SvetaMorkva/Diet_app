package com.kpi.iasa.dietapp;

import com.kpi.iasa.dietapp.persistence.dao.daoimpl.UserImpl;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTest {
    @Autowired
    private UserImpl dao;
    private User testUser = null;
    private final String testLogin = "Somebody";
    private final String testPassword = "seghd";

    private void updateLogin(String login) {
        testUser.setLoginName(login);
        var user = dao.update(testUser);
        assertThat(user, notNullValue());
        assertThat(user.getLoginName(), is(login));
        assertThat(user, is(testUser));
    }

    @BeforeAll
    void setup() {
        assertThat(dao, notNullValue());
        testUser = dao.add(new User(testLogin, testPassword));
        assertThat(testUser, notNullValue());
    }

    @Test
    public void daoAdd() {
        assertThat(testUser, is(new User(testLogin, testPassword)));
    }

    @Test
    public void daoUpdate() {
        updateLogin(testLogin + "2");
        updateLogin(testLogin);
    }

    @Test
    public void daoFindAll() {
        var userslistBeforeAdd = dao.findAll().size();
        var user = dao.add(new User("ggsdfb", "hbjgd"));
        assertThat(user, notNullValue());
        var userslistAfterAdd = dao.findAll().size();
        assert(userslistBeforeAdd + 1 == userslistAfterAdd);
        dao.delete(user);
        var userslistAfterDelete = dao.findAll().size();
        assert(userslistBeforeAdd == userslistAfterDelete);
    }

    @Test
    public void daoDelete() {
        var user = dao.add(new User("ggsdfb", "hbjgd"));
        assertThat(user, notNullValue());
        dao.delete(user);
        var nullUser = dao.findById(user.getId());
        assert(nullUser.isEmpty());
    }

    @AfterAll
    void close() {
        dao.delete(testUser);
    }
}