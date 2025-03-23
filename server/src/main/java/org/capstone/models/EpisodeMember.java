package org.capstone.models;

import java.util.Objects;

public class EpisodeMember {

    private int id;

    private int episodeId;

    private int castMemberId;

    private int episodeScore;

    public EpisodeMember() {
    }

    public EpisodeMember(int id, int episodeId, int castMemberId, int episodeScore) {
        this.id = id;
        this.episodeId = episodeId;
        this.castMemberId = castMemberId;
        this.episodeScore = episodeScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public int getCastMemberId() {
        return castMemberId;
    }

    public void setCastMemberId(int castMemberId) {
        this.castMemberId = castMemberId;
    }

    public int getEpisodeScore() {
        return episodeScore;
    }

    public void setEpisodeScore(int episodeScore) {
        this.episodeScore = episodeScore;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EpisodeMember that = (EpisodeMember) o;
        return id == that.id && episodeId == that.episodeId && castMemberId == that.castMemberId && episodeScore == that.episodeScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, episodeId, castMemberId, episodeScore);
    }
}
