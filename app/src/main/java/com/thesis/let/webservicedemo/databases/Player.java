package com.thesis.let.webservicedemo.databases;

public class Player {
    final int id;
    final String name;
    final String score;

    Player(int id, String name, String score) {
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
