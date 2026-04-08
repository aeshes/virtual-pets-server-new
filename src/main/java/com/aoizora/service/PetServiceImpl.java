package com.aoizora.service;

import com.aoizora.api.dto.*;
import com.aoizora.dao.*;
import com.aoizora.dao.domain.*;
import com.aoizora.service.exception.PetNotFoundException;
import com.aoizora.service.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    private final PetDao petDao;
    private final RoomDao roomDao;
    private final LevelDao levelDao;
    private final UserDao userDao;
    private final PetFoodDao petFoodDao;

    private final Clock clock;

    public PetServiceImpl(PetDao petDao, RoomDao roomDao, LevelDao levelDao, UserDao userDao, PetFoodDao petFoodDao, Clock clock) {
        this.petDao = petDao;
        this.roomDao = roomDao;
        this.levelDao = levelDao;
        this.userDao = userDao;
        this.petFoodDao = petFoodDao;
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
    @PreAuthorize("hasRole('USER')")
    @Transactional(rollbackFor = ServiceException.class)
    public void drink(Integer userId, DrinkRequest request) throws ServiceException {
        Pet pet = petDao.findByIdWithDrinksAndJournalEntriesAndAchievements(request.petId()).orElseThrow();
        Map<DrinkId, PetDrink> drinks = pet.getDrinks();
        PetDrink petDrink = drinks.get(request.drinkId());
        petDrink.setDrinkCount(petDrink.getDrinkCount() - 1);
        pet.setDrink(100);

        if (pet.getJournalEntries().get(JournalEntryId.BUILD_REFRIGERATOR) == null) {
            PetJournalEntry newPetJournalEntry = new PetJournalEntry();
            newPetJournalEntry.setCreatedAt(OffsetDateTime.now(clock));
            newPetJournalEntry.setPet(pet);
            newPetJournalEntry.setJournalEntry(JournalEntryId.BUILD_REFRIGERATOR);
            newPetJournalEntry.setReaded(false);
            pet.getJournalEntries().put(newPetJournalEntry.getJournalEntry(), newPetJournalEntry);
        }

        if (pet.getDrinkCount() < Integer.MAX_VALUE)
            pet.setDrinkCount(pet.getDrinkCount() + 1);
        if (pet.getDrinkCount() == 1)
            addAchievementIfNot(pet, AchievementId.DRINK_1);
        if (pet.getDrinkCount() == 10)
            addAchievementIfNot(pet, AchievementId.DRINK_10);
        if (pet.getDrinkCount() == 100)
            addAchievementIfNot(pet, AchievementId.DRINK_100);

        addExperience(pet, 1);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    @Transactional(rollbackFor = ServiceException.class)
    public void satiety(Integer userId, SatietyRequest request) throws ServiceException {
        Pet pet = petDao.findByIdWithFoodsJournalEntriesAndAchievements(request.petId()).orElseThrow();
        PetFood food = petFoodDao.findByPetIdAndFoodType(pet.getId(), request.foodId()).orElseThrow();

        if (food == null) {
            throw new ServiceException("Food count = 0.");
        } else {
            if (food.getFoodCount() == 0) {
                throw new ServiceException("Food count = 0.");
            }
            food.setFoodCount(food.getFoodCount() - 1);
        }

        pet.setSatiety(100);

        if (pet.getJournalEntries().get(JournalEntryId.BUILD_BOOKCASE) == null) {
            PetJournalEntry newPetJournalEntry = new PetJournalEntry();
            newPetJournalEntry.setCreatedAt(OffsetDateTime.now(clock));
            newPetJournalEntry.setPet(pet);
            newPetJournalEntry.setJournalEntry(JournalEntryId.BUILD_BOOKCASE);
            newPetJournalEntry.setReaded(false);
            pet.getJournalEntries().put(newPetJournalEntry.getJournalEntry(), newPetJournalEntry);
        }

        if (pet.getEatCount() < Integer.MAX_VALUE)
            pet.setEatCount(pet.getEatCount() + 1);
        if (pet.getEatCount() == 1)
            addAchievementIfNot(pet, AchievementId.FEED_1);
        if (pet.getEatCount() == 10)
            addAchievementIfNot(pet, AchievementId.FEED_10);
        if (pet.getEatCount() == 100)
            addAchievementIfNot(pet, AchievementId.FEED_100);

        addExperience(pet, 1);
    }

    @Override
    public void addExperience(Pet pet, Integer exp) {
        int nextExperience = pet.getExperience() + exp;
        Optional<Level> nextLevelOpt = levelDao.findById(pet.getLevel().getId() + 1);
        nextLevelOpt.ifPresentOrElse((nextLevel) -> {
            pet.setExperience(nextExperience);
            if (nextExperience >= nextLevel.getExperience()) {
                pet.setLevel(nextLevel);
            }
        }, () -> {
            Level lastLevel = levelDao.findById(pet.getLevel().getId()).orElseThrow();
            pet.setExperience(Math.min(nextExperience, lastLevel.getExperience()));
        });
    }

    @Override
    public void addAchievementIfNot(Pet pet, AchievementId achievement) {
        if (!pet.getAchievements().containsKey(achievement)) {
            PetAchievement petAchievement = new PetAchievement();
            petAchievement.setPet(pet);
            petAchievement.setAchievement(achievement);
            pet.getAchievements().put(achievement, petAchievement);
        }
    }

    @Override
    public PetDetails petInformationPage(Integer id) throws PetNotFoundException {
        Pet fullPet = petDao.findByIdWithJournalEntriesAndAchievements(id).orElseThrow(PetNotFoundException::new);
        PetDetails result = new PetDetails();
        result.setId(fullPet.getId());
        result.setName(fullPet.getName());
        result.setLevel(fullPet.getLevel().getId());
        result.setExperience(fullPet.getExperience());
        List<AchievementDTO> achievements = new ArrayList<>();
        result.setAchievements(achievements);

        for (AchievementId achievementId : AchievementId.values()) {
            AchievementDTO achievement = new AchievementDTO();
            achievement.setCode(achievementId.name());
            achievement.setUnlocked(fullPet.getAchievements().containsKey(achievementId));
            achievements.add(achievement);
        }

        return result;
    }

    @Override
    public Iterable<Pet> findLastCreatedPets(int start, int limit) {
        return petDao.findLastCreatedPets(start, limit);
    }
}
