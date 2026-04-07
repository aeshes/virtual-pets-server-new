package com.aoizora.dao;

import com.aoizora.dao.domain.*;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface PetFoodDao extends ListCrudRepository<PetFood, Integer>, JpaSpecificationExecutor<PetFood> {

    List<PetFood> findByPetId(Integer petId);

    List<PetFood> findByPet(Pet pet);

    default Optional<PetFood> findByPetIdAndFoodType(Integer petId, FoodId foodType) {
        return this.findAll(PetFoodDao.findByPetIdAndFoodTypeSpecification(petId, foodType), PageRequest.of(0, 1))
                .stream().findFirst();
    }

    private static Specification<PetFood> findByPetIdAndFoodTypeSpecification(Integer petId, FoodId foodType) {
        return (root, query, builder) -> {
            Predicate predicatePetId = builder.equal(root.get(PetFood_.pet).get(Pet_.id), petId);
            Predicate predicateFoodType = builder.equal(root.get(PetFood_.food).get(Food_.id), foodType);
            return builder.and(predicatePetId, predicateFoodType);
        };
    }
}
