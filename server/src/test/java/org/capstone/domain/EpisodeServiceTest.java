package org.capstone.domain;

import org.capstone.data.interfaces.EpisodeRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.Episode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EpisodeServiceTest {

    @MockBean
    EpisodeRepository repository;

    @Autowired
    EpisodeService service;

    @Test
    void findEpisodeById() {
        // Arrange
        int id = 1;
        Episode episode = new Episode();
        episode.setId(id);
        episode.setTitle("Test Episode");

        when(repository.findEpisodeById(id)).thenReturn(episode);

        // Act
        Result<Episode> result = service.findEpisodeById(id);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(episode, result.getPayload());
    }

    @Test
    void findEpisodeByIdNotFound() {
        // Arrange
        int id = 1;

        when(repository.findEpisodeById(id)).thenReturn(null);

        // Act
        Result<Episode> result = service.findEpisodeById(id);

        // Assert
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void findAllEpisodes() {
        // Arrange
        Episode episode = new Episode();
        episode.setId(1);
        episode.setTitle("Test Episode");

        when(repository.findAllEpisodes()).thenReturn(List.of(episode));

        // Act
        Result<List<Episode>> result = service.findAllEpisodes();

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(1, result.getPayload().size());
        assertEquals(episode, result.getPayload().get(0));
    }}