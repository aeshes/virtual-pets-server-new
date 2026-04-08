package com.aoizora.dao;

import com.aoizora.dao.domain.Pet;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PetDao {
    Optional<Pet> findFullById(Integer id);

    Optional<Pet> findByIdWithFullCloths(Integer id);

    Optional<Pet> findByIdWithJournalEntriesAndAchievements(Integer id);

    Optional<Pet> findByIdWithFullFoods(Integer id);

    Optional<Pet> findByIdWithDrinksAndJournalEntriesAndAchievements(Integer id);

    Optional<Pet> findByIdWithFoodsJournalEntriesAndAchievements(Integer id);

    void save(Pet pet);

    void delete(Pet pet);

    Page<Pet> findLastCreatedPets(int start, int limit);
}
