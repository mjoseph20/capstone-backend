package org.capstone.domain;

import org.capstone.data.interfaces.EpisodeRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.Episode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {

    private final EpisodeRepository repository;

    public EpisodeService(EpisodeRepository repository) {
        this.repository = repository;
    }

    public Result<Episode> findEpisodeById(int id) {
        Result<Episode> result = new Result<>();
        Episode foundEpisode = repository.findEpisodeById(id);

        if (foundEpisode == null) {
            result.addMessage("Episode not found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundEpisode);
        }

        return result;
    }

    public Result<List<Episode>> findAllEpisodes(){
        Result<List<Episode>> result = new Result<>();
        List<Episode> foundEpisodes = repository.findAllEpisodes();

        if (foundEpisodes == null) {
            result.addMessage("No episodes found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundEpisodes);
        }

        return result;
    }
}
