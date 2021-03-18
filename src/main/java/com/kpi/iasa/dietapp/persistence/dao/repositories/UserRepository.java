package com.kpi.iasa.dietapp.persistence.dao.repositories;

import com.kpi.iasa.dietapp.persistence.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginName(String loginName);
}