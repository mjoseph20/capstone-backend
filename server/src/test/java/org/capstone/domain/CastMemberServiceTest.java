package org.capstone.domain;

import org.capstone.data.interfaces.CastMemberRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.CastMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
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
        CastMember castMember = createCastMember(id);

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
    void findActiveCastMembers() {
        // Arrange
        CastMember castMember = createCastMember(1);

        when(repository.findActiveCastMembers()).thenReturn(List.of(castMember));

        // Act
        Result<List<CastMember>> result = service.findActiveCastMembers();

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(1, result.getPayload().size());
        assertEquals(castMember, result.getPayload().get(0));
    }

    @Test
    void findActiveCastMembersNotFound() {
        // Arrange
        when(repository.findActiveCastMembers()).thenReturn(null);

        // Act
        Result<List<CastMember>> result = service.findActiveCastMembers();

        // Assert
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void findAllCastMembers() {
        // Arrange
        CastMember castMember = createCastMember(1);

        when(repository.findAllCastMembers()).thenReturn(List.of(castMember));

        // Act
        Result<List<CastMember>> result = service.findAllCastMembers();

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(1, result.getPayload().size());
        assertEquals(castMember, result.getPayload().get(0));
    }

    @Test
    void addCastMemberToTeam() {
        // Arrange
        int castMemberId = 1;
        CastMember castMember = createCastMember(castMemberId);

        when(repository.findCastMemberById(castMemberId)).thenReturn(castMember);
        when(repository.addCastMemberToTeam(castMemberId)).thenReturn(true);

        // Act
        Result<CastMember> result = service.addCastMemberToTeam(castMemberId);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(castMember, result.getPayload());
    }

    @Test
    void addCastMemberToTeamNotFound() {
        // Arrange
        int castMemberId = 1;

        when(repository.findCastMemberById(castMemberId)).thenReturn(null);

        // Act
        Result<CastMember> result = service.addCastMemberToTeam(castMemberId);

        // Assert
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void addCastMemberToTeamMaxCastMembers() {
        // Arrange
        int castMemberId = 1;
        CastMember castMember = createCastMember(castMemberId);

        when(repository.findCastMemberById(castMemberId)).thenReturn(castMember);
        when(repository.addCastMemberToTeam(castMemberId)).thenReturn(true);

        // Act
        Result<CastMember> result = service.addCastMemberToTeam(castMemberId);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void addCastMemberToTeamFailed() {
        // Arrange
        int castMemberId = 1;
        CastMember castMember = createCastMember(castMemberId);

        when(repository.findCastMemberById(castMemberId)).thenReturn(castMember);
        when(repository.addCastMemberToTeam(castMemberId)).thenReturn(false);

        // Act
        Result<CastMember> result = service.addCastMemberToTeam(castMemberId);

        // Assert
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(castMember, result.getPayload());
    }

    @Test
    void addCastMemberToTeamMaxCastMembersFailed() {
        // Arrange
        int castMemberId = 1;
        CastMember castMember = createCastMember(castMemberId);

        when(repository.findCastMemberById(castMemberId)).thenReturn(castMember);
        when(repository.addCastMemberToTeam(castMemberId)).thenReturn(true);

        // Act
        Result<CastMember> result = service.addCastMemberToTeam(castMemberId);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(castMember, result.getPayload());
    }

    @Test
    void swapCastMemberForAnother() {
        // Arrange
        int castMemberId = 1;
        int newCastMemberId = 2;
        CastMember castMember = new CastMember();
        castMember.setId(castMemberId);
        castMember.setName("Test Cast Member");
        castMember.setActive(true);

        CastMember newCastMember = new CastMember();
        newCastMember.setId(newCastMemberId);
        newCastMember.setName("Test Cast Member 2");

        when(repository.findCastMemberById(castMemberId)).thenReturn(castMember);
        when(repository.findCastMemberById(newCastMemberId)).thenReturn(newCastMember);
        when(repository.swapCastMemberForAnother(castMemberId, newCastMemberId)).thenReturn(true);

        // Act
        Result<CastMember> result = service.swapCastMemberForAnother(castMemberId, newCastMemberId);

        // Assert
        //assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(castMember, result.getPayload());
    }

    @Test
    void swapCastMemberForAnotherNewNotFound() {
        // Arrange
        int castMemberId = 1;
        int newCastMemberId = 999;
        CastMember castMember = createCastMember(castMemberId);

        when(repository.findCastMemberById(castMemberId)).thenReturn(castMember);
        when(repository.findCastMemberById(newCastMemberId)).thenReturn(null);

        // Act
        Result<CastMember> result = service.swapCastMemberForAnother(castMemberId, newCastMemberId);

        // Assert
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void swapCastMemberForAnotherFailed() {
        // Arrange
        int castMemberId = 1;
        int newCastMemberId = 99;
        CastMember castMember = createCastMember(castMemberId);

        CastMember newCastMember = createCastMember(newCastMemberId);

        when(repository.findCastMemberById(castMemberId)).thenReturn(castMember);
        when(repository.findCastMemberById(newCastMemberId)).thenReturn(newCastMember);
        when(repository.swapCastMemberForAnother(castMemberId, newCastMemberId)).thenReturn(false);

        // Act
        Result<CastMember> result = service.swapCastMemberForAnother(castMemberId, newCastMemberId);

        // Assert
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void removeCastMemberFromTeam() {
        // Arrange
        int castMemberId = 1;
        CastMember castMember = createCastMember(castMemberId);
        castMember.setActive(true);

        when(repository.findCastMemberById(castMemberId)).thenReturn(castMember);
        when(repository.removeCastMemberFromTeam(castMemberId)).thenReturn(true);

        // Act
        Result<CastMember> result = service.removeCastMemberFromTeam(castMemberId);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(castMember, result.getPayload());
    }

    @Test
    void removeCastMemberFromTeamNotFound() {
        // Arrange
        int castMemberId = 1;

        when(repository.findCastMemberById(castMemberId)).thenReturn(null);

        // Act
        Result<CastMember> result = service.removeCastMemberFromTeam(castMemberId);

        // Assert
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void removeCastMemberFromTeamFailed() {
        // Arrange
        int castMemberId = 1;
        CastMember castMember = new CastMember();
        castMember.setId(castMemberId);
        castMember.setName("Test Cast Member");

        when(repository.removeCastMemberFromTeam(castMemberId)).thenReturn(false);

        // Act
        Result<CastMember> result = service.removeCastMemberFromTeam(castMemberId);

        // Assert
        assertEquals(ResultType.INVALID, result.getType());
        assertNull(result.getPayload());
    }

    public static CastMember createCastMember(int id) {
        CastMember castMember = new CastMember();
        castMember.setId(id);
        castMember.setName("Test Cast Member");
        castMember.setProfessionalTitle("Test Professional Title");
        castMember.setBiography("Test Bio");
        castMember.setBirthDate(LocalDate.of(1990, 1, 1));
        castMember.setRandomFact("Test Random Fact");
        return castMember;
    }
}