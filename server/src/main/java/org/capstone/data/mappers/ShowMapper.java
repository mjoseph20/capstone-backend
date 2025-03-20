package org.capstone.data.mappers;

import org.capstone.models.Show;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowMapper implements RowMapper<Show> {
    @Override
    public Show mapRow(ResultSet rs, int rowNum) throws SQLException {
        Show show = new Show();
        show.setId(rs.getInt("id"));
        show.setName(rs.getString("name"));
        show.setStartDate(rs.getDate("start_date").toLocalDate());
        show.setEndDate(rs.getDate("end_date").toLocalDate());
        show.setRating(rs.getBigDecimal("rating"));
        show.setCreator(rs.getString("creator"));
        show.setGenre(rs.getString("genre"));
        show.setStoryline(rs.getString("storyline"));
        show.setProductionCompany(rs.getString("production_company"));
        return show;
    }
}
