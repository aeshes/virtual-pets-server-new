package com.aoizora.service;

import com.aoizora.api.dto.RegistrationRequest;
import com.aoizora.api.dto.ServerInfoDTO;
import com.aoizora.service.exception.ServiceException;

public interface PublicService {
    ServerInfoDTO getServerInfo() throws ServiceException;

    void register(RegistrationRequest request) throws ServiceException;
}
