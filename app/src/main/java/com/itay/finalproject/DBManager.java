package com.itay.finalproject;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.itay.finalproject.models.AppUser;
import com.itay.finalproject.models.Workout;

import java.util.List;

public class DBManager {

    public static AppUser currentUser;
    private static CollectionReference usersRef;
    private static CollectionReference workoutsRef;

    private static CollectionReference getUsersRef() {
        if (usersRef == null) {
            usersRef = FirebaseFirestore.getInstance()
                    .collection("users");
        }
        return usersRef;
    }


    private static CollectionReference getWorkoutsRef() {
        if (workoutsRef == null) {
            workoutsRef = FirebaseFirestore.getInstance()
                    .collection("workouts");
        }
        return workoutsRef;
    }

    // add & edit
    public static void editUser(AppUser user, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        DBManager.currentUser = user;
        getUsersRef().document(user.getId()).set(currentUser)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }
    // add & edit
    public static void saveUser(OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        getUsersRef().document(currentUser.getId()).set(currentUser)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }

    public static void checkExistingAndAssign(String uid, OnFirestoreObjectListener<AppUser> listener) {
        getUsersRef().document(uid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (!documentSnapshot.exists())
                        listener.onSuccess(null);
                    else {
                        DBManager.currentUser = documentSnapshot.toObject(AppUser.class);
                        listener.onSuccess(currentUser);
                    }
                })
                .addOnFailureListener(listener::onFailure);
    }

    public static void addWorkoutToUser(Workout workout, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener) {

        currentUser.getDiary().add(workout);
        editUser(currentUser, onSuccessListener, onFailureListener);

    }

    public static void getWorkouts(OnFirestoreObjectListener<List<Workout>> listener) {

        getWorkoutsRef().addSnapshotListener((value, error) -> {
            if (error != null) {
                listener.onFailure(error);

            } else if (value != null) {
                List<Workout> workouts = value.toObjects(Workout.class);
                listener.onSuccess(workouts);
            } else {
                listener.onFailure(new Exception("Unknown error has occurred"));
            }
        });
    }


}
