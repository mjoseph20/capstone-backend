package org.capstone.domain;

import org.capstone.data.interfaces.ShowRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.Show;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ShowServiceTest {

    @MockBean
    ShowRepository repository;

    @Autowired
    ShowService service;

    @Test
    void findShowById() {
        // Arrange
        int id = 1;
        Show show = new Show();
        show.setId(id);
        show.setName("Test Show");

        when(repository.findShowById(id)).thenReturn(show);

        // Act
        Result<Show> result = service.findShowById(id);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(show, result.getPayload());
    }

    @Test
    void findShowByIdNotFound() {
        // Arrange
        int id = 1;

        when(repository.findShowById(id)).thenReturn(null);

        // Act
        Result<Show> result = service.findShowById(id);

        // Assert
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void findAllShows() {
        // Arrange
        Show show1 = new Show();
        show1.setId(1);
        show1.setName("Test Show 1");

        Show show2 = new Show();
        show2.setId(2);
        show2.setName("Test Show 2");

        when(repository.findAllShows()).thenReturn(List.of(show1, show2));

        // Act
        Result<List<Show>> result = service.findAllShows();

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(2, result.getPayload().size());
        assertEquals(show1, result.getPayload().get(0));
        assertEquals(show2, result.getPayload().get(1));
    }

}