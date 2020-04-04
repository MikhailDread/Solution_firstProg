package ru.solutionfirstprog.mantis.model;


import org.hibernate.annotations.Entity;


import java.util.Objects;


@Entity
public class UserHb {


    private int id;


    private String username;


    private String email;


    private String password;


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHb userHB = (UserHb) o;
        return id == userHB.id &&
                Objects.equals(username, userHB.username) &&
                Objects.equals(email, userHB.email) &&
                Objects.equals(password, userHB.password);
    }

    @Override
    public String toString() {
        return "UserHB{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password);
    }
}