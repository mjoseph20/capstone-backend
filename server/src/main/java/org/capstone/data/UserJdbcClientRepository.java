package org.capstone.data;

import org.capstone.data.interfaces.UserRepository;
import org.capstone.data.mappers.RegisteredUserMapper;
import org.capstone.models.RegisteredUser;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.List;

public class UserJdbcClientRepository implements UserRepository {

    private final String SELECT = "SELECT * FROM Registered_Users";

    private final JdbcClient client;

    public UserJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public RegisteredUser findUserById(int id) {

        final String sql = SELECT + " WHERE id = ?";

        return client.sql(sql)
                .param(id)
                .query(new RegisteredUserMapper())
                .optional()
                .orElse(null);
    }

    @Override
    public List<RegisteredUser> findAllUsers() {

        return client.sql(SELECT + ";").query(new RegisteredUserMapper()).list();
    }

    @Override
    public RegisteredUser createUser(RegisteredUser user) {

        final String sql = """
                INSERT INTO Registered_Users (username, password, name, email, score)
                VALUES (:username, :password, :name, :email, :score)
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        client.sql(sql)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("score", user.getScore())
                .update(keyHolder, "id");

        user.setId(keyHolder.getKey().intValue());

        return user;
    }

    @Override
    public boolean updateUser(RegisteredUser user) {

        final String sql = """
                UPDATE Registered_Users SET
                    username = :username,
                    password = :password,
                    name = :name,
                    email = :email,
                WHERE id = :id
                """;

        return client.sql(sql)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("id", user.getId())
                .update() > 0;
    }

    @Override
    public boolean deleteUser(int id) {

        return client.sql("DELETE FROM Registered_Users WHERE id = ?").param(id).update() > 0;
    }
}
