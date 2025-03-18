package org.capstone.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class CastMember {

    private int id;

    private String name;

    private String professionalTitle;

    private String biography;

    private LocalDate birthDate;

    private String randomFact;

    private int userId;

    public CastMember() {
    }

    public CastMember(int id, String name, String professionalTitle, String biography, LocalDate birthDate, String randomFact, int userId) {
        this.id = id;
        this.name = name;
        this.professionalTitle = professionalTitle;
        this.biography = biography;
        this.birthDate = birthDate;
        this.randomFact = randomFact;
        this.userId = userId;
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

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getRandomFact() {
        return randomFact;
    }

    public void setRandomFact(String randomFact) {
        this.randomFact = randomFact;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CastMember that = (CastMember) o;
        return id == that.id && userId == that.userId && Objects.equals(name, that.name) && Objects.equals(professionalTitle, that.professionalTitle) && Objects.equals(biography, that.biography) && Objects.equals(birthDate, that.birthDate) && Objects.equals(randomFact, that.randomFact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, professionalTitle, biography, birthDate, randomFact, userId);
    }
}
