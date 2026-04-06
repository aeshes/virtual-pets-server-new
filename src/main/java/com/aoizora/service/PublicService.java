package com.aoizora.service;

import com.aoizora.api.dto.ServerInfoDTO;
import com.aoizora.service.exception.ServiceException;

public interface PublicService {
    ServerInfoDTO getServerInfo() throws ServiceException;
}
