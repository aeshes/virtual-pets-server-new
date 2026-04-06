package com.aoizora.service;

import com.aoizora.dao.PetDao;
import com.aoizora.dao.RoomDao;
import com.aoizora.dao.domain.Pet;
import com.aoizora.service.exception.PetNotFoundException;
import com.aoizora.service.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetServiceImpl implements PetService {

    private final PetDao petDao;
    private final RoomDao roomDao;

    public PetServiceImpl(PetDao petDao, RoomDao roomDao) {
        this.petDao = petDao;
        this.roomDao = roomDao;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(rollbackFor = ServiceException.class)
    public void delete(Integer userId, Integer petId) throws ServiceException {
        Pet pet = petDao.findFullById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));
        if (pet.getUser().getId().equals(userId)) {
            roomDao.findByPetId(petId).ifPresent(roomDao::delete);
            petDao.delete(pet);
        } else {
            throw new PetNotFoundException(petId);
        }
    }
}
