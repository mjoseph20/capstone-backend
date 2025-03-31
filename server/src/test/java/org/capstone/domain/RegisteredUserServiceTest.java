package org.capstone.domain;

import org.capstone.data.interfaces.RegisteredUserRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.RegisteredUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RegisteredUserServiceTest {

    @MockBean
    RegisteredUserRepository repository;

    @Autowired
    RegisteredUserService service;

    @Nested
    class ReadTests {

        @Test
        void shouldFindUserById() {
            Result<RegisteredUser> expected = new Result<>();
            RegisteredUser expectedUser = expected.getPayload();

            when(repository.findUserById(1)).thenReturn(expectedUser);
            Result<RegisteredUser> actual = service.findUserById(1);

            assertEquals(expectedUser, actual.getPayload());
        }

        @Test
        void shouldNotFindUserById() {
            when(repository.findUserById(1)).thenReturn(null);
            Result<RegisteredUser> actual = service.findUserById(1);
            assertNull(actual.getPayload());
        }
    }

    @Nested
    class CreateTests {
        @Test
        void shouldCreateUser() {
            RegisteredUser user = new RegisteredUser();
            user.setUsername("user4");
            user.setPassword("password4");
            user.setName("User Four");
            user.setEmail("user4@email.com");


            RegisteredUser expected = new RegisteredUser();
            expected.setId(4);
            expected.setUsername("user4");
            expected.setPassword("password4");
            expected.setName("User Four");
            expected.setEmail("user4@email.com");

            when(repository.createUser(user)).thenReturn(expected);
            var result = service.createUser(user);

            assertTrue(result.isSuccess());
            assertEquals(expected, result.getPayload());
        }

        @Test
        void shouldNotCreateUserWhenUsernameBlank() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.createUser(user)).thenReturn(null);
            var result = service.createUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotCreateUserWhenPasswordBlank() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.createUser(user)).thenReturn(null);
            var result = service.createUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotCreateUserWhenNameBlank() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("");
            user.setEmail("userone@email.com");

            when(repository.createUser(user)).thenReturn(null);
            var result = service.createUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotCreateUserWhenEmailBlank() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("");

            when(repository.createUser(user)).thenReturn(null);
            var result = service.createUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotCreateUserWhenEmailInvalid() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("useroneemail.com");

            when(repository.createUser(user)).thenReturn(null);
            var result = service.createUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotCreateUserWhenPasswordInvalid() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("pass1");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.createUser(user)).thenReturn(null);
            var result = service.createUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotCreateUserWhenUsernameDuplicate() {
            RegisteredUser user = new RegisteredUser();
            user.setId(5);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.findAllUsers()).thenReturn(List.of(user));
            Result<RegisteredUser> expected = new Result<>();
            expected.addMessage("Username is already taken", ResultType.INVALID);
            Result<RegisteredUser> actual = service.createUser(user);

            assertFalse(actual.isSuccess());
            assertNull(actual.getPayload());
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotCreateUserWhenEmailDuplicate() {
            RegisteredUser user = new RegisteredUser();
            user.setId(4);
            user.setUsername("user4");
            user.setPassword("password4");
            user.setName("User Four");
            user.setEmail("userfour@email.com");

            RegisteredUser userToAdd = new RegisteredUser();
            userToAdd.setId(5);
            userToAdd.setUsername("user5");
            userToAdd.setPassword("password5");
            userToAdd.setName("User Five");
            userToAdd.setEmail("userfour@email.com");

            when(repository.findAllUsers()).thenReturn(List.of(user));
            Result<RegisteredUser> expected = new Result<>();
            expected.addMessage("Email is already taken", ResultType.INVALID);
            Result<RegisteredUser> actual = service.createUser(userToAdd);

            assertFalse(actual.isSuccess());
            assertNull(actual.getPayload());
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateTests {

        @Test
        void shouldUpdateUser() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.updateUser(user)).thenReturn(true);
            var result = service.updateUser(user);

            assertTrue(result.isSuccess());
            assertEquals(user, result.getPayload());
        }

        @Test
        void shouldNotUpdateUserWhenUsernameBlank() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.updateUser(user)).thenReturn(false);
            var result = service.updateUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotUpdateUserWhenPasswordBlank() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.updateUser(user)).thenReturn(false);
            var result = service.updateUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotUpdateUserWhenNameBlank() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("");
            user.setEmail("userone@email.com");

            when(repository.updateUser(user)).thenReturn(false);
            var result = service.updateUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotUpdateUserWhenEmailBlank() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("");

            when(repository.updateUser(user)).thenReturn(false);
            var result = service.updateUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotUpdateUserWhenEmailInvalid() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("useroneemail.com");

            when(repository.updateUser(user)).thenReturn(false);
            var result = service.updateUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotUpdateUserWhenPasswordInvalid() {
            RegisteredUser user = new RegisteredUser();
            user.setId(1);
            user.setUsername("user1");
            user.setPassword("pass1");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.updateUser(user)).thenReturn(false);
            var result = service.updateUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }

        @Test
        void shouldNotUpdateUserWhenUsernameDuplicate() {
            RegisteredUser user = new RegisteredUser();
            user.setId(5);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.findAllUsers()).thenReturn(List.of(user));
            Result<RegisteredUser> expected = new Result<>();
            expected.addMessage("Username is already taken", ResultType.INVALID);
            Result<RegisteredUser> actual = service.updateUser(user);

            assertFalse(actual.isSuccess());
            assertNull(actual.getPayload());
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotUpdateUserWhenEmailDuplicate() {
            RegisteredUser user = new RegisteredUser();
            user.setId(4);
            user.setUsername("user4");
            user.setPassword("password4");
            user.setName("User Four");
            user.setEmail("userfour@email.com");

            RegisteredUser userToUpdate = new RegisteredUser();
            userToUpdate.setId(5);
            userToUpdate.setUsername("user5");
            userToUpdate.setPassword("password5");
            userToUpdate.setName("User Five");
            userToUpdate.setEmail("userfour@email.com");

            when(repository.findAllUsers()).thenReturn(List.of(user));
            Result<RegisteredUser> expected = new Result<>();
            expected.addMessage("Email is already taken", ResultType.INVALID);
            Result<RegisteredUser> actual = service.updateUser(userToUpdate);

            assertFalse(actual.isSuccess());
            assertNull(actual.getPayload());
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotUpdateUserWhenUserNotFound() {
            RegisteredUser user = new RegisteredUser();
            user.setId(999);
            user.setUsername("user1");
            user.setPassword("password1");
            user.setName("User One");
            user.setEmail("userone@email.com");

            when(repository.updateUser(user)).thenReturn(false);
            var result = service.updateUser(user);

            assertFalse(result.isSuccess());
            assertNull(result.getPayload());
        }
    }

    @Nested
    class DeleteTests {

        @Test
        void shouldDeleteUser() {
            when(repository.deleteUser(1)).thenReturn(true);
            var result = service.deleteUser(1);
            assertTrue(result.isSuccess());
        }

        @Test
        void shouldNotDeleteUser() {
            when(repository.deleteUser(1)).thenReturn(false);
            var result = service.deleteUser(1);
            assertFalse(result.isSuccess());
        }
    }
}