package com.aoizora.dao;

import com.aoizora.dao.domain.Pet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PetDaoImpl implements PetDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Optional<Pet> findFullById(Integer id) {
        TypedQuery<Pet> query = em.createNamedQuery("Pet.findFullById", Pet.class);
        query.setParameter("id", id);
        List<Pet> pets = query.getResultList();
        return DataAccessUtils.optionalResult(pets);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pet> findByIdWithFullCloths(Integer id) {
        Pet pet = em.find(Pet.class, id, Map.of(
                "jakarta.persistence.fetchgraph",
                em.getEntityGraph("pet.cloths")));

        return Optional.ofNullable(pet);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pet> findByIdWithJournalEntriesAndAchievements(Integer id) {
        Pet pet = em.find(Pet.class, id, Map.of(
                "jakarta.persistence.fetchgraph",
                em.getEntityGraph("pet.journalEntriesAndAchievements")));

        return Optional.ofNullable(pet);
    }

    @Override
    public Optional<Pet> findByIdWithFullFoods(Integer id) {
        Pet pet = em.find(Pet.class, id, Map.of(
                "jakarta.persistence.fetchgraph",
                em.getEntityGraph("pet.foods")));

        return Optional.ofNullable(pet);
    }
}
