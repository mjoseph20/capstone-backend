package org.capstone.controllers;

import org.capstone.domain.CastMemberService;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.CastMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cast-members")
@CrossOrigin(origins = {"http://localhost:8080"})
public class CastMemberController {

    private final CastMemberService service;

    public CastMemberController(CastMemberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> findAllCastMembers() {
        Result<List<CastMember>> result = service.findAllCastMembers();

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @GetMapping("/active")
    public ResponseEntity<Object> findActiveCastMembers() {
        Result<List<CastMember>> result = service.findActiveCastMembers();

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @GetMapping("/{castMemberId}")
    public ResponseEntity<Object> findCastMemberById(@PathVariable int castMemberId) {
        Result<CastMember> result = service.findCastMemberById(castMemberId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @PutMapping("/add/{castMemberId}")
    public ResponseEntity<Object> addCastMemberToTeam(@PathVariable int castMemberId) {
        Result<CastMember> result = service.addCastMemberToTeam(castMemberId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @PutMapping("/remove/{castMemberId}")
    public ResponseEntity<Object> removeCastMemberFromTeam(@PathVariable int castMemberId) {
        Result<CastMember> result = service.removeCastMemberFromTeam(castMemberId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }

    @PutMapping("/swap/{castMemberId}/{newCastMemberId}")
    public ResponseEntity<Object> swapCastMembers(@PathVariable int castMemberId, @PathVariable int newCastMemberId) {
        Result<CastMember> result = service.swapCastMemberForAnother(castMemberId, newCastMemberId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }
}
