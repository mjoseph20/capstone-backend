package org.capstone.data;

import org.capstone.models.EpisodeMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EpisodeMemberJdbcClientRepositoryTest {

    @Autowired
    EpisodeMemberJdbcClientRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        EpisodeMember episodeMember = repository.findEpisodeMemberById(1);
        assertNotNull(episodeMember);
        assertEquals(1, episodeMember.getEpisodeId());
    }

    @Test
    void shouldNotFindMissing() {
        EpisodeMember episodeMember = repository.findEpisodeMemberById(1000);
        assertNull(episodeMember);
    }

    @Test
    void shouldFindAll() {
        assertEquals(5, repository.findAllEpisodeMembers().size());
    }

    @Test
    void shouldFindEpisodeMembersByEpisodeId() {
        assertEquals(1, repository.findEpisodeMembersByEpisodeId(1).size());
    }

    @Test
    void shouldFindEpisodeMembersByMemberId() {
        assertEquals(2, repository.findEpisodeMembersByMemberId(1).size());
    }
}