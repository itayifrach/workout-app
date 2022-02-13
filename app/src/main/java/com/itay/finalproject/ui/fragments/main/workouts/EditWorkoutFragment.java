package com.itay.finalproject.ui.fragments.main.workouts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itay.finalproject.R;
import com.itay.finalproject.models.Workout;
import com.squareup.picasso.Picasso;

public class EditWorkoutFragment extends Fragment {


    private ImageView d1,d2,d3,workoutIv;
    private EditText repsEt,setsEt;
    private Button saveWorkoutBtn,deleteWorkoutBtn;
    private TextView workoutNameTv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void findViews(View view) {
        d1 = view.findViewById(R.id.intensity_d1_edit);
        d2 = view.findViewById(R.id.intensity_d2_edit);
        d3 = view.findViewById(R.id.intensity_d3_edit);
        repsEt = view.findViewById(R.id.num_reps_et_edit_workout);
        setsEt = view.findViewById(R.id.num_sets_et_edit_Workout);
        saveWorkoutBtn = view.findViewById(R.id.saveWorkoutBtn);
        deleteWorkoutBtn = view.findViewById(R.id.deleteWorkoutBtn);
        workoutNameTv = view.findViewById(R.id.workout_name_tv_edit);
        workoutIv = view.findViewById(R.id.workout_iv_edit);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleWorkOut();
    }
    private void handleWorkOut() {
        assert getArguments()!=null;
        Gson g = new Gson();
        String workOutJson = getArguments().getString("workout");
        Workout workout = g.fromJson(workOutJson,Workout.class);
        workoutNameTv.setText(workout.getWorkoutName());
        Picasso.get().load(workout.getWorkoutImage()).into(workoutIv);
        repsEt.setHint(workout.getNumOfReps() +"");
        setsEt.setHint(workout.getNumOfSets() +"");
        d1.setVisibility(View.INVISIBLE);
        d2.setVisibility(View.INVISIBLE);
        d3.setVisibility(View.INVISIBLE);

        if (workout.getIntensity() == 1) {
            d1.setVisibility(View.VISIBLE);
        }else if (workout.getIntensity() == 2) {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
        }else {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
        }
    }
}