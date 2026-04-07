package com.aoizora.service;

import com.aoizora.api.dto.CreatePetRequest;
import com.aoizora.api.dto.DrinkRequest;
import com.aoizora.dao.domain.AchievementId;
import com.aoizora.dao.domain.Pet;
import com.aoizora.service.exception.ServiceException;

public interface PetService {
    void create(Integer userId, CreatePetRequest request) throws ServiceException;
    void delete(Integer userId, Integer petId) throws ServiceException;
    void drink(Integer userId, DrinkRequest request) throws ServiceException;
    void addExperience(Pet pet, Integer exp);
    void addAchievementIfNot(Pet pet, AchievementId achievementId);
}
