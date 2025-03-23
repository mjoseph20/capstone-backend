package org.capstone.controllers;

import org.capstone.controllers.helpers.*;
import org.capstone.domain.RegisteredUserService;
import org.capstone.domain.helpers.*;
import org.capstone.models.RegisteredUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registeredUser")
@CrossOrigin(origins = {"http://localhost:5500"})
public class RegisteredUserController {

    private final RegisteredUserService service;

    public RegisteredUserController(RegisteredUserService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> findUserById(@PathVariable int userId) {
        Result<RegisteredUser> result = service.findUserById(userId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody RegisteredUser user) {
         Result<RegisteredUser> result = service.createUser(user);
         if (result.isSuccess()) {
             return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
         }
            return ErrorResponse.build(result);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody RegisteredUser user) {
        Result<RegisteredUser> result = service.findUserByUsername(user.getUsername());

        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.NOT_FOUND);
        }

        if (result.getPayload().getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Password does not match.", HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody RegisteredUser user) {
        if (userId != user.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<RegisteredUser> result = service.updateUser(user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int userId) {
        Result<RegisteredUser> result = service.deleteUser(userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
