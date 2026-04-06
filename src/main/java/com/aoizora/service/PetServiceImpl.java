package com.aoizora.service;

import com.aoizora.api.dto.CreatePetRequest;
import com.aoizora.api.dto.DrinkRequest;
import com.aoizora.dao.LevelDao;
import com.aoizora.dao.PetDao;
import com.aoizora.dao.RoomDao;
import com.aoizora.dao.UserDao;
import com.aoizora.dao.domain.Pet;
import com.aoizora.service.exception.PetNotFoundException;
import com.aoizora.service.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.OffsetDateTime;

@Service
public class PetServiceImpl implements PetService {

    private final PetDao petDao;
    private final RoomDao roomDao;
    private final LevelDao levelDao;
    private final UserDao userDao;

    private final Clock clock;

    public PetServiceImpl(PetDao petDao, RoomDao roomDao, LevelDao levelDao, UserDao userDao, Clock clock) {
        this.petDao = petDao;
        this.roomDao = roomDao;
        this.levelDao = levelDao;
        this.userDao = userDao;
        this.clock = clock;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(rollbackFor = ServiceException.class)
    public void create(Integer userId, CreatePetRequest request) throws ServiceException {
        Pet pet = new Pet();
        pet.setName(request.name());
        pet.setCreatedDate(OffsetDateTime.now(clock));
        pet.setUser(userDao.getReferenceById(userId));
        pet.setComment(request.comment());
        pet.setPetType(request.petType());
        pet.setLevel(levelDao.findById(1).orElseThrow());
        petDao.save(pet);
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

    @Override
    public void drink(Integer userId, DrinkRequest request) throws ServiceException {

    }
}
