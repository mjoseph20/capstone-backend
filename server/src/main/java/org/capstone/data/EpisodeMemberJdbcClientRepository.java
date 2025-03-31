package org.capstone.data;

import org.capstone.data.interfaces.EpisodeMemberRepository;
import org.capstone.data.mappers.EpisodeMemberMapper;
import org.capstone.models.EpisodeMember;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EpisodeMemberJdbcClientRepository implements EpisodeMemberRepository {

    private final String SELECT = "SELECT * FROM Episode_Member";

    private final JdbcClient jdbcClient;

    public EpisodeMemberJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<EpisodeMember> findAllEpisodeMembers() {
        return jdbcClient.sql(SELECT + ";")
                .query(new EpisodeMemberMapper())
                .list();
    }

    @Override
    public EpisodeMember findEpisodeMemberById(int id) {
        return jdbcClient.sql(SELECT + " WHERE id = ?;")
                .param(id)
                .query(new EpisodeMemberMapper())
                .optional()
                .orElse(null);
    }

    @Override
    public List<EpisodeMember> findEpisodeMembersByEpisodeId(int episodeId) {
        return jdbcClient.sql(SELECT + " WHERE episode_id = ?;")
                .param(episodeId)
                .query(new EpisodeMemberMapper())
                .list();
    }

    @Override
    public List<EpisodeMember> findEpisodeMembersByMemberId(int memberId) {
        return jdbcClient.sql(SELECT + " WHERE cast_member_id = ?;")
                .param(memberId)
                .query(new EpisodeMemberMapper())
                .list();
    }
}
