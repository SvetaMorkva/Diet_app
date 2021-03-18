package com.kpi.iasa.dietapp.persistence.dao.daoimpl;

import com.kpi.iasa.dietapp.persistence.dao.IUser;
import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import com.kpi.iasa.dietapp.persistence.dao.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserImpl implements IUser {
    private final UserRepository repository;

    public User add(User entity) {
        return repository.save(entity);
    }

    public User update(User entity) {
        return repository.save(entity);
    }

    public void delete(User entity) {
        repository.delete(entity);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public User findByLogin(String login) {
        return repository.findByLoginName(login);
    }
}
