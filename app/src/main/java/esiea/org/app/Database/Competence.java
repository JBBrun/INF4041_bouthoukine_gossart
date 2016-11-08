package esiea.org.app.Database;

import java.util.List;

/**
 * Created by Nicolas on 08/11/2016.
 */

public class Competence {

    private Integer id;
    private String firstCompetence;
    private String secondCompetences;
    private String experience;
    private Integer user_id;

    public Competence() {
    }

    public Competence(Integer id, String firstCompetence, String secondCompetences, String experience) {
        this.id = id;
        this.firstCompetence = firstCompetence;
        this.secondCompetences = secondCompetences;
        this.experience = experience;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstCompetence() {
        return firstCompetence;
    }

    public void setFirstCompetence(String firstCompetence) {
        this.firstCompetence = firstCompetence;
    }

    public String getSecondCompetences() {
        return secondCompetences;
    }

    public void setSecondCompetences(String secondCompetences) {
        this.secondCompetences = secondCompetences;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}

