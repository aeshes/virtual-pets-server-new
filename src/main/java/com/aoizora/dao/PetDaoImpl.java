package com.aoizora.dao;

import com.aoizora.dao.domain.Pet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    @Transactional(readOnly = true)
    public Optional<Pet> findByIdWithFullFoods(Integer id) {
        Pet pet = em.find(Pet.class, id, Map.of(
                "jakarta.persistence.fetchgraph",
                em.getEntityGraph("pet.foods")));

        return Optional.ofNullable(pet);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pet> findByIdWithDrinksAndJournalEntriesAndAchievements(Integer id) {
        Pet pet = em.find(Pet.class, id, Map.of(
                "jakarta.persistence.fetchgraph",
                em.getEntityGraph("pet.drinksAndJournalEntriesAndAchievements")));

        return Optional.ofNullable(pet);
    }

    @Override
    public Optional<Pet> findByIdWithFoodsJournalEntriesAndAchievements(Integer id) {
        Pet pet = em.find(Pet.class, id, Map.of(
                "jakarta.persistence.fetchgraph",
                em.getEntityGraph("pet.foodsAndJournalEntriesAndAchievements")));

        return Optional.ofNullable(pet);
    }

    @Override
    public void save(Pet pet) {
        em.persist(pet);
    }

    @Override
    public void delete(Pet pet) {
        em.remove(pet);
    }

    @Override
    public Page<Pet> findLastCreatedPets(int start, int limit) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Pet> cq = cb.createQuery(Pet.class);
        Root<Pet> root = cq.from(Pet.class);
        cq.select(root).orderBy(cb.desc(root.get("created_date")));

        TypedQuery<Pet> query = em.createQuery(cq);
        query.setFirstResult(start);
        query.setMaxResults(limit);
        List<Pet> pets = query.getResultList();

        CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
        Root<Pet> countRoot = countCq.from(Pet.class);
        countCq.select(cb.count(countRoot));

        long total = em.createQuery(countCq).getSingleResult();

        return new PageImpl<>(pets, PageRequest.of(start / limit, limit), total);
    }
}
