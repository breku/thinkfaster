package com.horse.dao;

import com.horse.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by brekol on 15.01.15.
 */
@Transactional
@Repository
public interface HorseDao extends JpaRepository<Horse, Long> {
}
