package com.itay.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.FirestoreGrpc;
import com.itay.finalproject.models.AppUser;
import com.itay.finalproject.models.Workout;

import java.util.List;

public class DBManager {


    private static CollectionReference usersRef;
    private static CollectionReference workoutsRef;
    private static CollectionReference getUsersRef() {
        if(usersRef ==null) {
            usersRef = FirebaseFirestore.getInstance()
                    .collection("users");
        }
        return usersRef;
    }


    private static CollectionReference getWorkoutsRef() {
        if(workoutsRef ==null) {
            workoutsRef = FirebaseFirestore.getInstance()
                    .collection("workouts");
        }
        return workoutsRef;
    }

    public static void addUser(AppUser user, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        getUsersRef().document(user.getId()).set(user)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }


    public static void getWorkouts(OnFirestoreObjectListener<List<Workout>> listener)  {

        getWorkoutsRef().addSnapshotListener((value, error) -> {
            if(error!=null) {
                listener.onFailure(error);

            }
           else if(value !=null) {
               List<Workout> workouts = value.toObjects(Workout.class);
               listener.onSuccess(workouts);
           }else  {
              listener.onFailure(new Exception("Unknown error has occurred"));
           }
        });
    }


}
