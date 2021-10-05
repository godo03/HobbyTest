package com.thesis.let.webservicedemo.databases;

public class PlayerBooks {
    final int id;
    final String name;
    final String score;

    PlayerBooks(int id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getScore() {return score;}
}
