package org.capstone.data;

import org.capstone.models.CastMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CastMemberJdbcClientRepositoryTest {

    @Autowired
    CastMemberJdbcClientRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        CastMember castMember = repository.findCastMemberById(1);
        assertNotNull(castMember);
        assertEquals("Steve Carell", castMember.getName());
    }

    @Test
    void shouldNotFindMissing() {
        CastMember castMember = repository.findCastMemberById(1000);
        assertNull(castMember);
    }

    @Test
    void shouldFindAll() {
        assertEquals(3, repository.findAllCastMembers().size());
    }

}