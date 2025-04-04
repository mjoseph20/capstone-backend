package org.capstone.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Episode {

    private int id;

    private int season;

    private int episodeNumber;

    private String title;

    private String description;

    private LocalDate airDate;

    private BigDecimal rating;

    private int showId;

    private String imageUrl;

    public Episode() {
    }

    public Episode(int id, int season, int episodeNumber, String title, String description, LocalDate airDate, BigDecimal rating, String imageUrl, int showId) {
        this.id = id;
        this.season = season;
        this.episodeNumber = episodeNumber;
        this.title = title;
        this.description = description;
        this.airDate = airDate;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.showId = showId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAirDate() {
        return airDate;
    }

    public void setAirDate(LocalDate airDate) {
        this.airDate = airDate;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode = (Episode) o;
        return id == episode.id && season == episode.season && episodeNumber == episode.episodeNumber && showId == episode.showId && Objects.equals(title, episode.title) && Objects.equals(description, episode.description) && Objects.equals(airDate, episode.airDate) && Objects.equals(rating, episode.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, season, episodeNumber, title, description, airDate, rating, showId);
    }
}
