package com.example.appkacovid;

public class User {
    private String login,haslo;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                '}';
    }

    public User(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
