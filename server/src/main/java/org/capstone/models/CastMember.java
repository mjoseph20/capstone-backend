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

    private boolean isActive;

    public CastMember() {
    }

    public CastMember(int id, String name, String professionalTitle, String biography, LocalDate birthDate, String randomFact, boolean isActive) {
        this.id = id;
        this.name = name;
        this.professionalTitle = professionalTitle;
        this.biography = biography;
        this.birthDate = birthDate;
        this.randomFact = randomFact;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CastMember that = (CastMember) o;
        return id == that.id && isActive == that.isActive && Objects.equals(name, that.name) && Objects.equals(professionalTitle, that.professionalTitle) && Objects.equals(biography, that.biography) && Objects.equals(birthDate, that.birthDate) && Objects.equals(randomFact, that.randomFact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, professionalTitle, biography, birthDate, randomFact, isActive);
    }
}
