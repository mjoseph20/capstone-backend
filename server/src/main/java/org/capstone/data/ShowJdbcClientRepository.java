package org.capstone.data;

import org.capstone.data.interfaces.ShowRepository;
import org.capstone.data.mappers.ShowMapper;
import org.capstone.models.Show;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShowJdbcClientRepository implements ShowRepository {

    private final String SELECT = "SELECT * FROM Shows";

    private final JdbcClient client;

    public ShowJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public Show findShowById(int id) {
        return client.sql(SELECT + " WHERE id = ?;")
                .param(id)
                .query(new ShowMapper())
                .optional()
                .orElse(null);
    }

    @Override
    public List<Show> findAllShows() {
        return client.sql(SELECT + ";")
                .query(new ShowMapper())
                .list();
    }
}
