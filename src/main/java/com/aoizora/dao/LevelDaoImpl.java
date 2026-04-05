package com.aoizora.dao;

import com.aoizora.dao.domain.Level;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LevelDaoImpl implements LevelDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Level> findById(Integer id) {
        Level level = em.find(Level.class, id);
        return Optional.ofNullable(level);
    }
}
