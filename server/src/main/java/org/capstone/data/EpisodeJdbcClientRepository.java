package org.capstone.data;

import org.capstone.data.interfaces.EpisodeRepository;
import org.capstone.data.mappers.EpisodeMapper;
import org.capstone.models.Episode;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EpisodeJdbcClientRepository implements EpisodeRepository {

    private final String SELECT = "SELECT * FROM Episodes";

    private final JdbcClient client;

    public EpisodeJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public Episode findEpisodeById(int id) {
        return client.sql(SELECT + " WHERE id = ?;")
                .param(id)
                .query(new EpisodeMapper())
                .optional()
                .orElse(null);
    }

    @Override
    public List<Episode> findAllEpisodes() {
        return client.sql(SELECT + ";")
                .query(new EpisodeMapper())
                .list();
    }
}
