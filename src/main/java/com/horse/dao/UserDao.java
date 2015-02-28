package com.horse.dao;

import com.horse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by brekol on 23.11.14.
 */
@Transactional
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    public User findByUsername(String username);
}

