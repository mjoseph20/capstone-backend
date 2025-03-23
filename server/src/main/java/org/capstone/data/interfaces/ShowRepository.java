package org.capstone.data.interfaces;

import org.capstone.models.Show;

import java.util.List;

public interface ShowRepository {

    Show findShowById(int id);

    List<Show> findAllShows();
}
