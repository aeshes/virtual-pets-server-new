package com.aoizora.service;

import com.aoizora.api.dto.CreatePetRequest;
import com.aoizora.service.exception.ServiceException;

public interface PetService {
    void create(Integer userId, CreatePetRequest request) throws ServiceException;
    void delete(Integer userId, Integer petId) throws ServiceException;
}
