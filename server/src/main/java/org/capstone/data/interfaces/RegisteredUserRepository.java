package org.capstone.data.interfaces;

import org.capstone.models.RegisteredUser;

import java.util.List;

public interface RegisteredUserRepository {

    RegisteredUser findUserById(int id);

    RegisteredUser findUserByUsername(String username);

    RegisteredUser findUserByEmail(String email);

    List<RegisteredUser> findAllUsers();

    RegisteredUser createUser(RegisteredUser user);

    boolean updateUser(RegisteredUser user);

    boolean deleteUser(int id);
}
