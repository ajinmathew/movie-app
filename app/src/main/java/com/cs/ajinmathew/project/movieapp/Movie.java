package com.cs.ajinmathew.project.movieapp;

public class Movie {

    public String movie_name;
    public String actor_name;
    public String actress_name;
    public String director;
    public String distributer;
    public String producer;
    public String camera;
    public String year;

    public Movie() {
    }

    public Movie(String movie_name, String actor_name, String actress_name, String director, String distributer, String producer, String camera, String year) {
        this.movie_name = movie_name;
        this.actor_name = actor_name;
        this.actress_name = actress_name;
        this.director = director;
        this.distributer = distributer;
        this.producer = producer;
        this.camera = camera;
        this.year = year;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getActor_name() {
        return actor_name;
    }

    public void setActor_name(String actor_name) {
        this.actor_name = actor_name;
    }

    public String getActress_name() {
        return actress_name;
    }

    public void setActress_name(String actress_name) {
        this.actress_name = actress_name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDistributer() {
        return distributer;
    }

    public void setDistributer(String distributer) {
        this.distributer = distributer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
