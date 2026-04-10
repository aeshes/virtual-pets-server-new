package com.aoizora.service;

import com.aoizora.api.dto.UserProfile;
import com.aoizora.dao.UserDao;
import com.aoizora.dao.domain.User;
import com.aoizora.service.exception.UserNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @PreAuthorize("hasRole('USER') && (#userId eq principal.userId)")
    public UserProfile getProfile(@P("userId") Integer userId) throws UserNotFoundException {
        UserProfile userProfile = new UserProfile();
        Optional<User> user = userDao.findById(userId);
        user.ifPresent(u -> {
            userProfile.setBirthdate(u.getBirthdate());
            userProfile.setName(u.getLogin());
            userProfile.setEmail(u.getEmail());
        });
        user.orElseThrow(() -> new UserNotFoundException(userId));

        return userProfile;
    }
}
