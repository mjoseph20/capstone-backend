package org.capstone.domain;

import org.capstone.data.interfaces.RegisteredUserRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.domain.helpers.Validations;
import org.capstone.models.RegisteredUser;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository repository;

    public RegisteredUserService(RegisteredUserRepository repository) {
        this.repository = repository;
    }

    public RegisteredUser findUserById(int id) {
        return repository.findUserById(id);
    }

    public Result<RegisteredUser> createUser(RegisteredUser user) {
        Result<RegisteredUser> result = validate(user);

        if (!result.isSuccess()) {
            return result;
        }

        try {
            RegisteredUser newUser = repository.createUser(user);
            result.setPayload(newUser);
        } catch (DuplicateKeyException ex) {
            result.addMessage("This user already exists", ResultType.INVALID);
        } catch (Exception ex) {
            result.addMessage("Could not create user", ResultType.INVALID);
        }

        return result;
    }

    public Result<RegisteredUser> updateUser(RegisteredUser user) {
        Result<RegisteredUser> result = validate(user);

        if (!result.isSuccess()) {
            return result;
        }

        boolean success = repository.updateUser(user);

        if (!success) {
            result.addMessage("Could not find user for update", ResultType.NOT_FOUND);
        } else {
            result.setPayload(user);
        }

        return result;
    }

    public Result<RegisteredUser> deleteUser(int id) {
        Result<RegisteredUser> result = new Result<>();

        boolean success = repository.deleteUser(id);

        if (!success) {
            result.addMessage("Could not find user for delete", ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<RegisteredUser> validate(RegisteredUser user) {
        Result<RegisteredUser> result = new Result<>();

        if (Validations.isNullOrBlank(user.getUsername())) {
            result.addMessage("Username is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(user.getPassword())) {
            result.addMessage("Password is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(user.getName())) {
            result.addMessage("Name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(user.getEmail())) {
            result.addMessage("Email is required", ResultType.INVALID);
        }

        if (!Validations.isValidEmail(user.getEmail())) {
            result.addMessage("Email is invalid", ResultType.INVALID);
        }

        if (user.getPassword().length() < 8) {
            result.addMessage("Password must be at least 8 characters", ResultType.INVALID);
        }

        for (RegisteredUser existingUser : repository.findAllUsers()) {
            if (existingUser.getUsername().equalsIgnoreCase(user.getUsername())) {
                result.addMessage("Username is already taken", ResultType.INVALID);
                break;
            }
            if (existingUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                result.addMessage("Email is already taken", ResultType.INVALID);
                break;
            }
        }

        return result;
    }
}
