package com.aoizora.service;

import com.aoizora.service.exception.ServiceException;

public interface PetService {
    void delete(Integer userId, Integer petId) throws ServiceException;
}
