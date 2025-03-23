package org.capstone.domain;

import org.capstone.data.interfaces.ShowRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.Show;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository repository;

    public ShowService(ShowRepository repository) {
        this.repository = repository;
    }

    public Result<Show> findShowById(int id) {
        Result<Show> result = new Result<>();
        Show foundShow = repository.findShowById(id);

        if (foundShow == null) {
            result.addMessage("Show not found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundShow);
        }

        return result;
    }

    public Result<List<Show>> findAllShows(){
        Result<List<Show>> result = new Result<>();
        List<Show> foundShows = repository.findAllShows();

        if (foundShows == null) {
            result.addMessage("No shows found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundShows);
        }

        return result;
    }
}
