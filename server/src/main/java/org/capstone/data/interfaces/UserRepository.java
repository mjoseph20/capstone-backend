package org.capstone.data.interfaces;

import org.capstone.models.RegisteredUser;

import java.util.List;

public interface UserRepository {

    RegisteredUser findUserById(int id);

    List<RegisteredUser> findAllUsers();

    RegisteredUser createUser(RegisteredUser user);

    boolean updateUser(RegisteredUser user);

    boolean deleteUser(int id);
}
