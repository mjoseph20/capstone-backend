package org.capstone.data.interfaces;

import org.capstone.models.CastMember;

import java.util.List;

public interface CastMemberRepository {

    CastMember findCastMemberById(int id);

    List<CastMember> findAllCastMembers();

    boolean addCastMemberToTeam(int castMemberId);

    boolean swapCastMemberForAnother(int castMemberId, int newCastMemberId);

    boolean removeCastMemberFromTeam(int castMemberId);
}
