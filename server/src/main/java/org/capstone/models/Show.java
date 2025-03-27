package org.capstone.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Show {

    private int id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal rating;

    private String creator;

    private String genre;

    private String storyline;

    private String productionCompany;

    private String imageUrl;

    public Show() {
    }

    public Show(int id, String name, LocalDate startDate, LocalDate endDate, BigDecimal rating, String creator, String genre, String storyline, String productionCompany, String imageUrl) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rating = rating;
        this.creator = creator;
        this.genre = genre;
        this.storyline = storyline;
        this.productionCompany = productionCompany;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return id == show.id && Objects.equals(name, show.name) && Objects.equals(startDate, show.startDate) && Objects.equals(endDate, show.endDate) && Objects.equals(rating, show.rating) && Objects.equals(creator, show.creator) && Objects.equals(genre, show.genre) && Objects.equals(storyline, show.storyline) && Objects.equals(productionCompany, show.productionCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, rating, creator, genre, storyline, productionCompany);
    }
}
