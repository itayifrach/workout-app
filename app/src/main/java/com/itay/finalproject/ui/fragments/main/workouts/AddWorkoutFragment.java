package com.itay.finalproject.ui.fragments.main.workouts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.itay.finalproject.DBManager;
import com.itay.finalproject.R;
import com.itay.finalproject.models.Workout;
import com.squareup.picasso.Picasso;


public class AddWorkoutFragment extends Fragment {

    private ImageView d1,d2,d3,workoutIv;
    private EditText repsEt,setsEt;
    private Button addWorkoutBtn;
    private TextView workoutNameTv,workoutInstruction;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void findViews(View view) {
        d1 = view.findViewById(R.id.intensity_d1_add);
        d2 = view.findViewById(R.id.intensity_d2_add);
        d3 = view.findViewById(R.id.intensity_d3_add);
        repsEt = view.findViewById(R.id.num_reps_et_add_workout);
        setsEt = view.findViewById(R.id.num_sets_et_add_Workout);
        addWorkoutBtn = view.findViewById(R.id.addWorkoutBtn);
        workoutNameTv = view.findViewById(R.id.workout_name_tv_add);
        workoutIv = view.findViewById(R.id.workout_iv_add);
        workoutInstruction=view.findViewById(R.id.instruction_add);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleWorkOut();

        //click listeners
        addWorkoutBtn.setOnClickListener((v) -> {
            if (repsEt.getText().toString().isEmpty() || setsEt.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Please enter number of reps and sets", Toast.LENGTH_SHORT).show();
                return;
            }
            workout.setNumOfReps(Integer.parseInt(repsEt.getText().toString()));
            workout.setNumOfSets(Integer.parseInt(setsEt.getText().toString()));
            DBManager.addWorkoutToUser(workout, unused -> {
                Toast.makeText(getContext(), "Successfully workout " + workout.getWorkoutName(), Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(AddWorkoutFragment.this).popBackStack();
            }, e -> Toast.makeText(getContext(), "There was an error adding workout", Toast.LENGTH_SHORT).show());
        });
    }
    private Workout workout;
    private void handleWorkOut() {
        assert getArguments()!=null;
        Gson g = new Gson();
        String workOutJson = getArguments().getString("workout");
         workout = g.fromJson(workOutJson,Workout.class);
        workoutNameTv.setText(workout.getWorkoutName());
        workoutInstruction.setText(workout.getWorkoutInstruction());
        Picasso.get().load(workout.getWorkoutImage()).into(workoutIv);
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