package org.capstone.controllers;

import org.capstone.domain.ShowService;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.Show;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@CrossOrigin(origins = {"http://localhost:8080"})
public class ShowController {

    private final ShowService service;

    public ShowController(ShowService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> findAllShows() {
        Result<List<Show>> result = service.findAllShows();

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @GetMapping("/{showId}")
    public ResponseEntity<Object> findShowById(int showId) {
        Result<Show> result = service.findShowById(showId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }
}
