package com.aoizora.service;

import com.aoizora.api.dto.ServerInfoDTO;
import com.aoizora.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class PublicServiceImpl implements PublicService {

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
}
