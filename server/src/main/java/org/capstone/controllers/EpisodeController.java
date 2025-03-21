package org.capstone.controllers;

import org.capstone.domain.EpisodeService;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.Episode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
@CrossOrigin(origins = {"http://localhost:8080"})
public class EpisodeController {

    private final EpisodeService service;

    public EpisodeController(EpisodeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> findAllEpisodes() {
        Result<List<Episode>> result = service.findAllEpisodes();

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @GetMapping("/{episodeId}")
    public ResponseEntity<Object> findEpisodeById(int episodeId) {
        Result<Episode> result = service.findEpisodeById(episodeId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }
}
