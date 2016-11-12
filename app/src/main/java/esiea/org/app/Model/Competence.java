package esiea.org.app.Model;

import java.util.List;

/**
 * Created by Nicolas on 08/11/2016.
 */

public class Competence {

    private int id;
    private String name;


    public Competence(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Competence() {
    }

    public Integer getId() {
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
}

