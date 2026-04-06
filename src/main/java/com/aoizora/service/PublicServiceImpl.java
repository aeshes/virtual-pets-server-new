package com.aoizora.service;

import com.aoizora.api.dto.RegistrationRequest;
import com.aoizora.api.dto.ServerInfoDTO;
import com.aoizora.config.ApplicationSettings;
import com.aoizora.dao.UserDao;
import com.aoizora.dao.domain.Role;
import com.aoizora.dao.domain.User;
import com.aoizora.service.exception.IncompatibleVersionException;
import com.aoizora.service.exception.NameIsBusyException;
import com.aoizora.service.exception.ServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Service
public class PublicServiceImpl implements PublicService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationSettings properties;

    private final Clock clock;

    public PublicServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, ApplicationSettings properties, Clock clock) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.properties = properties;
        this.clock = clock;
    }


    @Override
    public ServerInfoDTO getServerInfo() throws ServiceException {
        try {
            Properties properties = System.getProperties();
            Map<String, String> infoMap = new HashMap<>();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                infoMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            return new ServerInfoDTO(infoMap);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void register(RegistrationRequest request) throws ServiceException {
        String clientVersion = request.version();
        if (!properties.getVersion().equals(clientVersion)) {
            throw new IncompatibleVersionException("", properties.getVersion(), clientVersion);
        }
        Optional<User> existUser = userDao.findByLogin(request.login());
        if (existUser.isPresent()) {
            throw new NameIsBusyException();
        }
        User user = new User();
        user.setLogin(request.login());
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setRegistrationDate(OffsetDateTime.now(clock));
        user.setRoles(Role.USER.name());
        user.setEnabled(true);
        userDao.save(user);
    }
}
