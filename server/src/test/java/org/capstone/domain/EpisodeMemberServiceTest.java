package org.capstone.domain;

import org.capstone.data.interfaces.EpisodeMemberRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.EpisodeMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EpisodeMemberServiceTest {

    @MockBean
    EpisodeMemberRepository repository;

    @Autowired
    EpisodeMemberService service;

    @Test
    void findEpisodeMemberById() {
        // Arrange
        int id = 1;
        EpisodeMember episodeMember = new EpisodeMember();
        episodeMember.setId(id);
        episodeMember.setEpisodeId(1);
        episodeMember.setCastMemberId(1);

        when(repository.findEpisodeMemberById(id)).thenReturn(episodeMember);

        // Act
        Result<EpisodeMember> result = service.findEpisodeMemberById(id);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(episodeMember, result.getPayload());
    }

    @Test
    void findEpisodeMemberByIdNotFound() {
        // Arrange
        int id = 1;

        when(repository.findEpisodeMemberById(id)).thenReturn(null);

        // Act
        Result<EpisodeMember> result = service.findEpisodeMemberById(id);

        // Assert
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertNull(result.getPayload());
    }

    @Test
    void findAllEpisodeMembers() {
        // Arrange
        EpisodeMember episodeMember = new EpisodeMember();
        episodeMember.setId(1);
        episodeMember.setEpisodeId(1);
        episodeMember.setCastMemberId(1);

        when(repository.findAllEpisodeMembers()).thenReturn(List.of(episodeMember));

        // Act
        Result<List<EpisodeMember>> result = service.findAllEpisodeMembers();

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(1, result.getPayload().size());
        assertEquals(episodeMember, result.getPayload().get(0));
    }

    @Test
    void findEpisodeMembersByEpisodeId() {
        // Arrange
        int episodeId = 1;
        EpisodeMember episodeMember = new EpisodeMember();
        episodeMember.setId(1);
        episodeMember.setEpisodeId(episodeId);
        episodeMember.setCastMemberId(1);

        when(repository.findEpisodeMembersByEpisodeId(episodeId)).thenReturn(List.of(episodeMember));

        // Act
        Result<List<EpisodeMember>> result = service.findEpisodeMembersByEpisodeId(episodeId);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(1, result.getPayload().size());
        assertEquals(episodeMember, result.getPayload().get(0));
    }

    @Test
    void findEpisodeMembersByMemberId() {
        // Arrange
        int memberId = 1;
        EpisodeMember episodeMember = new EpisodeMember();
        episodeMember.setId(1);
        episodeMember.setEpisodeId(1);
        episodeMember.setCastMemberId(memberId);

        when(repository.findEpisodeMembersByMemberId(memberId)).thenReturn(List.of(episodeMember));

        // Act
        Result<List<EpisodeMember>> result = service.findEpisodeMembersByMemberId(memberId);

        // Assert
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(1, result.getPayload().size());
        assertEquals(episodeMember, result.getPayload().get(0));
    }
}