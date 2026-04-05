package com.aoizora.dao;

import com.aoizora.dao.domain.Level;

import java.util.Optional;

public interface LevelDao {
    Optional<Level> findById(Integer id);
}
