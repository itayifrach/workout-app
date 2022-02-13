package com.itay.finalproject.models;

import java.util.ArrayList;
import java.util.List;

public class AppUser {
    private String id;
    private String email;
    private float height;
    private float weight;
    private int age;
    private List<Workout> diary;
    public AppUser(String id, String email, float height, float weight,int age) {
        this.id = id;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.diary = new ArrayList<>();
        this.age=age;
    }

    public AppUser(String id, String email, float height, float weight,int age, ArrayList<Workout> diary) {
       this(id,email,height,weight,age);
       this.diary = diary;
    }


    public AppUser() {

    }

    public List<Workout> getDiary() {
        return diary;
    }

    public void setDiary(List<Workout> diary) {
        this.diary = diary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", diary=" + diary +
                '}';
    }
}
