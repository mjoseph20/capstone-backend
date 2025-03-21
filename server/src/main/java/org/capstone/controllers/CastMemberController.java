package org.capstone.controllers;

import org.capstone.domain.CastMemberService;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.CastMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{castMemberId}")
    public ResponseEntity<Object> findCastMemberById(int castMemberId) {
        Result<CastMember> result = service.findCastMemberById(castMemberId);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }
}
