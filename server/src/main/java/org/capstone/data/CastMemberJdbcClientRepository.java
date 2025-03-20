package org.capstone.data;

import org.capstone.data.interfaces.CastMemberRepository;
import org.capstone.data.mappers.CastMemberMapper;
import org.capstone.models.CastMember;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

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
    public List<CastMember> findAllCastMembers() {
        return client.sql(SELECT + ";")
                .query(new CastMemberMapper())
                .list();
    }
}
