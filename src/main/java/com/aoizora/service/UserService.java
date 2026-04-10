package com.aoizora.service;

import com.aoizora.api.dto.UserProfile;
import com.aoizora.service.exception.UserNotFoundException;

public interface UserService {

    UserProfile getProfile(Integer id) throws UserNotFoundException;
}
