package com.aoizora.dao.mapper;

import com.aoizora.dao.domain.LastRegisteredUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LastRegisteredUserMapper implements RowMapper<LastRegisteredUser > {

    @Nullable
    @Override
    public LastRegisteredUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        LastRegisteredUser lastRegisteredUser = new LastRegisteredUser();
        lastRegisteredUser.setId(rs.getInt("id"));
        lastRegisteredUser.setRegistrationDate(rs.getDate("registration_date"));
        lastRegisteredUser.setName(rs.getString("name"));
        lastRegisteredUser.setPetsCount(rs.getLong("pets_count"));

        return lastRegisteredUser;
    }
}
