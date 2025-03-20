package org.capstone.data.interfaces;

import org.capstone.models.Episode;

import java.util.List;

public interface EpisodeRepository {

    Episode findEpisodeById(int id);

    List<Episode> findAllEpisodes();
}
