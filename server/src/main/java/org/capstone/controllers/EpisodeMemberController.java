package org.capstone.controllers;

import org.capstone.domain.EpisodeMemberService;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.EpisodeMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/episode-members")
@CrossOrigin(origins = {"http://localhost:8080"})
public class EpisodeMemberController {

    private final EpisodeMemberService service;

    public EpisodeMemberController(EpisodeMemberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> findAllEpisodeMembers() {
        Result<List<EpisodeMember>> result = service.findAllEpisodeMembers();

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @GetMapping("/{episodeMemberId}")
    public ResponseEntity<Object> findEpisodeMemberById(int episodeMemberId) {
        Result<EpisodeMember> result = service.findEpisodeMemberById(episodeMemberId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @GetMapping("/episode/{episodeId}")
    public ResponseEntity<Object> findEpisodeMembersByEpisodeId(int episodeId) {
        Result<List<EpisodeMember>> result = service.findEpisodeMembersByEpisodeId(episodeId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<Object> findEpisodeMembersByMemberId(int memberId) {
        Result<List<EpisodeMember>> result = service.findEpisodeMembersByMemberId(memberId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }
}
