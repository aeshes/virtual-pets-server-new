package com.aoizora.dao;

import com.aoizora.dao.domain.Room;

import java.util.Optional;

public interface RoomDao {
    Optional<Room> findByPetId(Integer petId);
}
