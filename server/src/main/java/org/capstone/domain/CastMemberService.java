package org.capstone.domain;

import org.capstone.data.interfaces.CastMemberRepository;
import org.capstone.data.interfaces.RegisteredUserRepository;
import org.capstone.domain.helpers.Result;
import org.capstone.domain.helpers.ResultType;
import org.capstone.domain.helpers.Validations;
import org.capstone.models.CastMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastMemberService {

    private final CastMemberRepository repository;

    private final RegisteredUserRepository userRepository;

    private final int MAX_CAST_MEMBERS = 5;

    public CastMemberService(CastMemberRepository repository, RegisteredUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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

    public Result<List<CastMember>> findActiveCastMembers(){
        Result<List<CastMember>> result = new Result<>();
        List<CastMember> foundCastMembers = repository.findActiveCastMembers();

        if (foundCastMembers == null) {
            result.addMessage("No active cast members found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(foundCastMembers);
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

    public Result<CastMember> addCastMemberToTeam(int castMemberId) {
        CastMember castMember = repository.findCastMemberById(castMemberId);
        Result<CastMember> result = validateCastMember(castMember);

        if (getCastMemberCountForUser() >= MAX_CAST_MEMBERS) {
            result.addMessage("User has reached the maximum number of cast members", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            try {
                boolean success = repository.addCastMemberToTeam(castMemberId);

                if (success) {
                    result.addMessage("Cast Member added to team", ResultType.SUCCESS);
                } else {
                    result.addMessage("Failed to add Cast Member to team", ResultType.INVALID);
                }
            } catch (Exception e) {
                result.addMessage("Cast Member is already associated with a user", ResultType.INVALID);
            }
        }

        return result;
    }

    public Result<CastMember> swapCastMemberForAnother(int castMemberId, int newCastMemberId) {

        CastMember castMember = repository.findCastMemberById(castMemberId);
        CastMember newCastMember = repository.findCastMemberById(newCastMemberId);

        Result<CastMember> result1 = validateCastMember(castMember);
        Result<CastMember> result2 = validateCastMember(newCastMember);

        if (castMember != null && !castMember.isActive()) {
            result1.addMessage("No User associated with this Cast Member", ResultType.INVALID);
        }

        if (!result2.isSuccess()) {
            result1.addMessage("New Cast Member is invalid", ResultType.INVALID);
        }

       if (result1.isSuccess() && result2.isSuccess()) {
            boolean success = repository.swapCastMemberForAnother(castMemberId, newCastMemberId);

            if (success) {
                result1.addMessage("Cast Member swapped for another", ResultType.SUCCESS);
            } else {
                result1.addMessage("Failed to swap Cast Member for another", ResultType.INVALID);
            }
        }

        return result1;
    }

    public Result<CastMember> removeCastMemberFromTeam(int castMemberId) {
        CastMember castMember = repository.findCastMemberById(castMemberId);
        Result<CastMember> result = validateCastMember(castMember);

        if (castMember != null && !castMember.isActive()) {
            result.addMessage("No User associated with this Cast Member", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            boolean success = repository.removeCastMemberFromTeam(castMemberId);

            if (success) {
                result.addMessage("Cast Member removed from team", ResultType.SUCCESS);
            } else {
                result.addMessage("Failed to remove Cast Member from team", ResultType.INVALID);
            }
        }

        return result;
    }

    private Result<CastMember> validateCastMember(CastMember castMember) {
        Result<CastMember> result = new Result<>();

        if (castMember == null) {
            result.addMessage("Cast Member cannot be null", ResultType.INVALID);
        } else if (Validations.isNullOrBlank(castMember.getName())) {
            result.addMessage("Cast Member name cannot be empty", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            result.setPayload(castMember);
        }

        return result;
    }

    private int getCastMemberCountForUser() {
        return repository.findAllCastMembers().stream()
                .filter(CastMember::isActive)
                .toList()
                .size();
    }
}
