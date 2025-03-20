package org.capstone.data;

import org.capstone.models.Show;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ShowJdbcClientRepositoryTest {

    @Autowired
    ShowJdbcClientRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        Show show = repository.findShowById(1);
        assertNotNull(show);
        assertEquals("show1", show.getName());
    }

    @Test
    void shouldNotFindMissing() {
        Show show = repository.findShowById(999);
        assertNull(show);
    }

    @Test
    void shouldFindAll() {
        assertEquals(3, repository.findAllShows().size());
    }
}