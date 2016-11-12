package esiea.org.app.Model;

/**
 * Created by Ayoub Bouthoukine on 07/11/2016.
 */
public class User {

    private int id ;
    private String name;
    private String email;
    private String password;
    private int profil;

    //constructors
    public User(String name,String email, String password, int profil)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profil = profil;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfil() {
        return profil;
    }

    public void setProfil(int profil) {
        this.profil = profil;
    }



}
