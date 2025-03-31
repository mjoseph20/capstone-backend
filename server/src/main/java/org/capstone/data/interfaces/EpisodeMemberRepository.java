package org.capstone.data.interfaces;

import org.capstone.models.EpisodeMember;

import java.util.List;

public interface EpisodeMemberRepository {

    List<EpisodeMember> findAllEpisodeMembers();

    EpisodeMember findEpisodeMemberById(int id);

    List<EpisodeMember> findEpisodeMembersByEpisodeId(int episodeId);

    List<EpisodeMember> findEpisodeMembersByMemberId(int memberId);
}
