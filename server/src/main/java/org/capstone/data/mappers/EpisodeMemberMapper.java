package org.capstone.data.mappers;

import org.capstone.models.EpisodeMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EpisodeMemberMapper implements RowMapper<EpisodeMember> {
    @Override
    public EpisodeMember mapRow(ResultSet rs, int rowNum) throws SQLException {
        EpisodeMember episodeMember = new EpisodeMember();
        episodeMember.setId(rs.getInt("id"));
        episodeMember.setEpisodeId(rs.getInt("episode_id"));
        episodeMember.setCastMemberId(rs.getInt("cast_member_id"));
        episodeMember.setEpisodeScore(rs.getInt("episode_score"));
        return episodeMember;
    }
}
