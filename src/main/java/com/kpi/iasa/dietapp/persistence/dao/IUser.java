package com.kpi.iasa.dietapp.persistence.dao;

import com.kpi.iasa.dietapp.persistence.dao.entities.User;

public interface IUser extends IBase<User> {
    User findByLogin(String login);
}
