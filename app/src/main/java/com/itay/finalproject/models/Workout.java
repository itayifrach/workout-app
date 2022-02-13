package com.itay.finalproject.models;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Map;

public class Workout  {
    private String workoutName;
    private String workoutImage;
    private String workoutInstruction;
    private int intensity; // 1-3
    private int numOfReps;
    private int numOfSets;
    private boolean dummy;
    private long addedAt;


    public Workout() {

    }

    public Workout(String workoutName, String workoutImage, String workoutInstruction,int intensity, int numOfReps, int numOfSets) {
        this.workoutName = workoutName;
        this.workoutImage = workoutImage;
        this.workoutInstruction = workoutInstruction;
        this.intensity = intensity;
        this.numOfReps = numOfReps;
        this.numOfSets = numOfSets;
        this.addedAt = System.currentTimeMillis();
    }

    public Workout(Workout other) {
        this.workoutName = other.workoutName;
        this.workoutImage = other.workoutImage;
        this.workoutInstruction = other.workoutInstruction;
        this.intensity = other.intensity;
        this.numOfReps = other.numOfReps;
        this.numOfSets = other.numOfSets;
        this.addedAt = other.getAddedAt();
    }
    public long getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(long addedAt) {
        this.addedAt = addedAt;
    }
    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getIntensity() {
        return intensity;
    }

    public String getWorkoutImage() {
        return workoutImage;
    }

    public void setWorkoutImage(String workoutImage) {
        this.workoutImage = workoutImage;
    }

    public String getWorkoutInstruction() {
        return workoutInstruction;
    }

    public void setWorkoutInstruction(String workoutInstruction) {
        this.workoutInstruction = workoutInstruction;
    }

     public boolean isDummy() {
        return workoutName.equals("dummy")
                || workoutImage.equals("dummy")
                || workoutInstruction.equals("dummy");
    }

    public int getNumOfReps() {
        return numOfReps;
    }

    public void setNumOfReps(int numOfReps) {
        this.numOfReps = numOfReps;
    }

    public int getNumOfSets() {
        return numOfSets;
    }

    public void setNumOfSets(int numOfSets) {
        this.numOfSets = numOfSets;
    }
}
