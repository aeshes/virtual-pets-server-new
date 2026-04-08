package com.aoizora.dao;

import com.aoizora.dao.mapper.LastRegisteredUserMapper;
import com.aoizora.dao.domain.LastRegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcReportDAO {

    private JdbcTemplate jdbc;

    private LastRegisteredUserMapper lastRegisteredUserMapper = new LastRegisteredUserMapper();

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Transactional(readOnly = true)
    public List<LastRegisteredUser> findLastRegisteredUsers(int start, int limit) {
        return jdbc.query("""
                select
                    u.id as id,
                    u.registration_date as registration_date,
                    u.name as name,
                    count(p.id) as pets_count
                from "user" u
                    left join pet p on p.user_id = u.id
                group by u.id, u.registration_date, u.name
                order by registration_date desc offset ? limit ?
                """, lastRegisteredUserMapper, start, limit);
    }
}
