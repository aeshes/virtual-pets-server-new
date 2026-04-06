package com.aoizora.dao;

import com.aoizora.dao.domain.Room;
import com.aoizora.dao.domain.Room_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomDaoImpl implements RoomDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Optional<Room> findByPetId(Integer petId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);
        Root<Room> root = query.from(Room.class);
        query.select(root);
        Predicate predicate = cb.equal(root.get(Room_.petId), petId);
        query.where(predicate);
        TypedQuery<Room> q = em.createQuery(query);
        List<Room> rooms = q.getResultList();

        return DataAccessUtils.optionalResult(rooms);
    }

    @Override
    public void delete(Room room) {
        em.remove(room);
    }
}
