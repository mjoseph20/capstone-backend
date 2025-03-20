package org.capstone.data.mappers;

import org.capstone.models.CastMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CastMemberMapper implements RowMapper<CastMember> {
    @Override
    public CastMember mapRow(ResultSet rs, int rowNum) throws SQLException {
        CastMember castMember = new CastMember();
        castMember.setId(rs.getInt("id"));
        castMember.setName(rs.getString("name"));
        castMember.setProfessionalTitle(rs.getString("professional_title"));
        castMember.setBiography(rs.getString("bio"));
        castMember.setBirthDate(rs.getDate("birth_date").toLocalDate());
        castMember.setRandomFact(rs.getString("random_fact"));
        castMember.setUserId(rs.getInt("user_id"));
        return castMember;
    }
}
