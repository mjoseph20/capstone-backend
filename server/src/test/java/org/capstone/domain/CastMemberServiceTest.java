package org.capstone.domain;

import org.capstone.data.interfaces.CastMemberRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.CastMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CastMemberServiceTest {

    @MockBean
    CastMemberRepository repository;

    @Autowired
    CastMemberService service;

    @Test
    void findCastMemberById() {
        // Arrange
        int id = 1;
        CastMember castMember = new CastMember();
        castMember.setId(id);
        castMember.setName("Test Cast Member");

        when(repository.findCastMemberById(id)).thenReturn(castMember);

        // Act
        Result<CastMember> result = service.findCastMemberById(id);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(castMember, result.getPayload());
    }

    @Test
    void findCastMemberByIdNotFound() {
        // Arrange
        int id = 1;

        when(repository.findCastMemberById(id)).thenReturn(null);

        // Act
        Result<CastMember> result = service.findCastMemberById(id);

        // Assert
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void findAllCastMembers() {
        // Arrange
        CastMember castMember = new CastMember();
        castMember.setId(1);
        castMember.setName("Test Cast Member");

        when(repository.findAllCastMembers()).thenReturn(List.of(castMember));

        // Act
        Result<List<CastMember>> result = service.findAllCastMembers();

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(1, result.getPayload().size());
        assertEquals(castMember, result.getPayload().get(0));
    }
}