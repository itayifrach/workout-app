package com.itay.finalproject.ui.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.gson.Gson;
import com.itay.finalproject.DBManager;
import com.itay.finalproject.OnFirestoreObjectListener;
import com.itay.finalproject.R;
import com.itay.finalproject.WorkoutsRvAdapter;
import com.itay.finalproject.models.AppUser;
import com.itay.finalproject.models.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutsFragment extends Fragment implements WorkoutsItemClickListener {

    private RecyclerView rvWorkouts;
    private WorkoutsRvAdapter rvWorkoutsAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_workouts,container,false);
    }

    private void findViews(View view) {
        rvWorkouts = view.findViewById(R.id.rvWorkouts);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         findViews(view);
         rvWorkouts.setLayoutManager(new LinearLayoutManager(getContext()));

         DBManager.getWorkouts(new OnFirestoreObjectListener<>() {
             @Override
             public void onSuccess(List<Workout> wks) {
                 rvWorkoutsAdapter = new WorkoutsRvAdapter(wks, false,WorkoutsFragment.this);
                 rvWorkouts.setAdapter(rvWorkoutsAdapter);
             }
             @Override
             public void onFailure(Exception e) {
                 System.out.println(e.getMessage());
             }
         });
    }

    @Override
    public void itemClicked(Workout workoutItem) {
        Bundle b = new Bundle();
        Gson g = new Gson();
        String workOutJson = g.toJson(workoutItem);
        b.putString("workout",workOutJson);
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_navigation_workouts_to_specificWorkoutFragment,b);
    }
}