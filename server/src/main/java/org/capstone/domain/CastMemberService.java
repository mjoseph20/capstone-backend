package org.capstone.domain;

import org.capstone.data.interfaces.CastMemberRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.models.CastMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastMemberService {

    private final CastMemberRepository repository;

    public CastMemberService(CastMemberRepository repository) {
        this.repository = repository;
    }

    public Result<CastMember> findCastMemberById(int id) {
        Result<CastMember> result = new Result<>();
        CastMember foundCastMember = repository.findCastMemberById(id);

        if (foundCastMember == null) {
            result.addMessage("Cast Member not found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundCastMember);
        }

        return result;
    }

    public Result<List<CastMember>> findAllCastMembers(){
        Result<List<CastMember>> result = new Result<>();
        List<CastMember> foundCastMembers = repository.findAllCastMembers();

        if (foundCastMembers == null) {
            result.addMessage("No cast members found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundCastMembers);
        }

        return result;
    }
}
