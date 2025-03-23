package org.capstone.domain;

import org.capstone.data.interfaces.EpisodeMemberRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.EpisodeMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeMemberService {

    private final EpisodeMemberRepository repository;

    public EpisodeMemberService(EpisodeMemberRepository repository) {
        this.repository = repository;
    }

    public Result<EpisodeMember> findEpisodeMemberById(int id) {
        Result<EpisodeMember> result = new Result<>();
        EpisodeMember foundEpisodeMember = repository.findEpisodeMemberById(id);

        if (foundEpisodeMember == null) {
            result.addMessage("Episode member not found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundEpisodeMember);
        }

        return result;
    }

    public Result<List<EpisodeMember>> findAllEpisodeMembers() {
        Result<List<EpisodeMember>> result = new Result<>();
        List<EpisodeMember> foundEpisodeMembers = repository.findAllEpisodeMembers();

        if (foundEpisodeMembers == null) {
            result.addMessage("No episode members found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundEpisodeMembers);
        }

        return result;
    }

    public Result<List<EpisodeMember>> findEpisodeMembersByEpisodeId(int episodeId) {
        Result<List<EpisodeMember>> result = new Result<>();
        List<EpisodeMember> foundEpisodeMembers = repository.findEpisodeMembersByEpisodeId(episodeId);

        if (foundEpisodeMembers == null) {
            result.addMessage("No episode members found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundEpisodeMembers);
        }

        return result;
    }

    public Result<List<EpisodeMember>> findEpisodeMembersByMemberId(int memberId) {
        Result<List<EpisodeMember>> result = new Result<>();
        List<EpisodeMember> foundEpisodeMembers = repository.findEpisodeMembersByMemberId(memberId);

        if (foundEpisodeMembers == null) {
            result.addMessage("No episode members found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundEpisodeMembers);
        }

        return result;
    }
}
