package org.capstone.data;

import org.capstone.models.RegisteredUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RegisteredUserJdbcClientRepositoryTest {

    @Autowired
    RegisteredUserJdbcClientRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        RegisteredUser user = repository.findUserById(1);
        assertNotNull(user);
        assertEquals("user1", user.getUsername());
    }

    @Test
    void shouldNotFindMissing() {
        RegisteredUser user = repository.findUserById(1000);
        assertNull(user);
    }

    @Test
    void shouldFindAll() {
        assertEquals(3, repository.findAllUsers().size());
    }

    @Test
    void shouldCreate() {
        RegisteredUser user = makeUser();
        user.setId(0);
        RegisteredUser actual = repository.createUser(user);
        assertNotNull(actual);
        assertEquals(4, actual.getId());
    }

    @Test
    void shouldUpdate() {
        RegisteredUser user = makeUser();
        user.setUsername("johndoe");
        user.setPassword("password123");
        user.setName("John Doe");
        user.setEmail("johndoe@email.com");

        assertTrue(repository.updateUser(user));

        RegisteredUser actual = repository.findUserById(1);
        assertNotNull(actual);
        assertEquals("johndoe", actual.getUsername());
        assertEquals("password123", actual.getPassword());
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteUser(1));
        assertNull(repository.findUserById(1));
    }

    private RegisteredUser makeUser() {
        RegisteredUser user = new RegisteredUser();
        user.setId(1);
        user.setUsername("user4");
        user.setPassword("password1");
        user.setName("User One");
        user.setEmail("userone@email.com");
        user.setScore(0);
        return user;
    }
}