package org.capstone.data;

import org.capstone.models.Episode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EpisodeJdbcClientRepositoryTest {

    @Autowired
    EpisodeJdbcClientRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        Episode episode = repository.findEpisodeById(1);
        assertNotNull(episode);
        assertEquals("Pilot", episode.getTitle());
    }

    @Test
    void shouldNotFindMissing() {
        Episode episode = repository.findEpisodeById(999);
        assertNull(episode);
    }

    @Test
    void shouldFindAll() {
        assertEquals(5, repository.findAllEpisodes().size());
    }

}