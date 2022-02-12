package com.itay.finalproject.models;

public class DiaryWorkout extends Workout{
    private long addedAt;

    public DiaryWorkout(long addedAt) {
        this.addedAt = addedAt;
    }

    public DiaryWorkout(String workoutName, String workoutImage, String workoutInstruction, int intensity, int numOfReps, int numOfSets, long addedAt) {
        super(workoutName, workoutImage, workoutInstruction, intensity, numOfReps, numOfSets);
        this.addedAt = addedAt;
    }

    public DiaryWorkout(Workout workout) {
        super(workout);
        this.addedAt = System.currentTimeMillis();
    }

    public long getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(long addedAt) {
        this.addedAt = addedAt;
    }
}
