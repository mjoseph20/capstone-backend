package org.capstone.data;

import org.capstone.data.interfaces.CastMemberRepository;
import org.capstone.data.mappers.CastMemberMapper;
import org.capstone.models.CastMember;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CastMemberJdbcClientRepository implements CastMemberRepository {

    private final String SELECT = "SELECT * FROM Cast_Members";

    private final JdbcClient client;

    public CastMemberJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public CastMember findCastMemberById(int id) {
        return client.sql(SELECT + " WHERE id = ?;")
                .param(id)
                .query(new CastMemberMapper())
                .optional()
                .orElse(null);
    }

    @Override
    public List<CastMember> findActiveCastMembers() {
        return client.sql(SELECT + " WHERE is_active = true;")
                .query(new CastMemberMapper())
                .list();
    }

    @Override
    public List<CastMember> findAllCastMembers() {
        return client.sql(SELECT + ";")
                .query(new CastMemberMapper())
                .list();
    }

    @Override
    public boolean addCastMemberToTeam(int castMemberId) {

        final String sql = """
                UPDATE Cast_Members SET
                    is_active = true
                WHERE id = ?;
                """;

        return client.sql(sql)
                .param(castMemberId)
                .update() > 0;
    }

    @Override
    @Transactional
    public boolean swapCastMemberForAnother(int castMemberId, int newCastMemberId) {

        return removeCastMemberFromTeam(castMemberId) && addCastMemberToTeam(newCastMemberId);
    }

    @Override
    public boolean removeCastMemberFromTeam(int castMemberId) {

        final String sql = """
                UPDATE Cast_Members SET
                    is_active = false
                WHERE id = ?;
                """;

        int FREE_AGENT_ID = 0;
        return client.sql(sql)
                .param(castMemberId)
                .update() > 0;
    }
}
