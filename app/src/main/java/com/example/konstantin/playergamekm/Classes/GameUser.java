package com.example.konstantin.playergamekm.Classes;

/**
 * Created by Konstantin on 03/02/2017.
 */

public class GameUser {


    private long id;
    private String name;
    private long wins;
    private long losses;
    private long ties;


    public GameUser() {

    }

    public GameUser(long id, String name, long wins, long losses, long ties) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public long getLosses() {
        return losses;
    }

    public void setLosses(long losses) {
        this.losses = losses;
    }

    public long getTies() {
        return ties;
    }

    public void setTies(long ties) {
        this.ties = ties;
    }


    @Override
    public String toString() {
        return this.name;
    }



}
