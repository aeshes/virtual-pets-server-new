package com.aoizora.dao;

import com.aoizora.dao.domain.Pet;

import java.util.Optional;

public interface PetDao {
    Optional<Pet> findFullById(Integer id);

    Optional<Pet> findByIdWithFullCloths(Integer id);

    Optional<Pet> findByIdWithJournalEntriesAndAchievements(Integer id);

    Optional<Pet> findByIdWithFullFoods(Integer id);
}
