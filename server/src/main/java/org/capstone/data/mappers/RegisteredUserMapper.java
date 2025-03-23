package org.capstone.data.mappers;

import org.capstone.models.RegisteredUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisteredUserMapper implements RowMapper<RegisteredUser> {

    @Override
    public RegisteredUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        RegisteredUser user = new RegisteredUser();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setScore(rs.getInt("score"));
        return user;
    }
}
