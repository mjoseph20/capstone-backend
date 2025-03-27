package org.capstone.data.mappers;

import org.capstone.models.Episode;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EpisodeMapper implements RowMapper<Episode> {
    @Override
    public Episode mapRow(ResultSet rs, int rowNum) throws SQLException {
        Episode episode = new Episode();
        episode.setId(rs.getInt("id"));
        episode.setSeason(rs.getInt("season"));
        episode.setEpisodeNumber(rs.getInt("episode_number"));
        episode.setTitle(rs.getString("title"));
        episode.setDescription(rs.getString("description"));
        episode.setAirDate(rs.getDate("air_date").toLocalDate());
        episode.setRating(rs.getBigDecimal("rating"));
        episode.setImageUrl(rs.getString("image_url"));
        episode.setShowId(rs.getInt("show_id"));
        return episode;
    }
}
