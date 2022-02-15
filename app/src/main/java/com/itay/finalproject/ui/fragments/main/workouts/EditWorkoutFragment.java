package com.itay.finalproject.ui.fragments.main.workouts;

import static com.itay.finalproject.DBManager.currentUser;
import static com.itay.finalproject.DBManager.editUser;
import static com.itay.finalproject.DBManager.getWorkouts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
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

public class EditWorkoutFragment extends Fragment {

    private Workout relatedWorkout;
    private ImageView d1,d2,d3,workoutIv;
    private EditText repsEt,setsEt;
    private Button saveWorkoutBtn,deleteWorkoutBtn;
    private TextView workoutNameTv,workoutInstruction;
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
        workoutInstruction=view.findViewById(R.id.instruction_edit);
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
        AlertDialog deleteAlert = new AlertDialog.Builder(getContext())
                .setMessage("Are you sure you want to delete " + relatedWorkout.getWorkoutName())
                .setPositiveButton("Yes", (dialog, which) -> DBManager.removeWorkOutFromUser(relatedWorkout, unused -> NavHostFragment.findNavController(EditWorkoutFragment.this).popBackStack(), e -> Toast.makeText(getContext(),"There was an error deleting this workout please try again",Toast.LENGTH_SHORT).show()))
                .setNegativeButton("Cancel",null).create();
       deleteWorkoutBtn.setOnClickListener(v -> deleteAlert.show());


        AlertDialog editAlert = new AlertDialog.Builder(getContext())
                .setMessage("Are you sure you want to save changes for  " + relatedWorkout.getWorkoutName())
                .setPositiveButton("Yes", (dialog, which) ->  {
                    if(repsEt.getText().toString().isEmpty() && setsEt.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(),"No changes were done",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!repsEt.getText().toString().isEmpty())
                        relatedWorkout.setNumOfReps(Integer.parseInt(repsEt.getText().toString()));
                    if(!setsEt.getText().toString().isEmpty())
                        relatedWorkout.setNumOfSets(Integer.parseInt(setsEt.getText().toString()));

                    DBManager.editWorkout(relatedWorkout, unused -> NavHostFragment.findNavController(EditWorkoutFragment.this).popBackStack(), e -> Toast.makeText(getContext(),"There was an error saving changes for this workout please try again",Toast.LENGTH_SHORT).show());
                })
                .setNegativeButton("Cancel",null).create();
        saveWorkoutBtn.setOnClickListener(v -> editAlert.show());
    }
    private void handleWorkOut() {
        assert getArguments()!=null;
        Gson g = new Gson();
        String workOutJson = getArguments().getString("workout");
        relatedWorkout = g.fromJson(workOutJson,Workout.class);
        workoutNameTv.setText(relatedWorkout.getWorkoutName());
        workoutInstruction.setText(relatedWorkout.getWorkoutInstruction());
        Picasso.get().load(relatedWorkout.getWorkoutImage()).into(workoutIv);
        repsEt.setHint(relatedWorkout.getNumOfReps() +"");
        setsEt.setHint(relatedWorkout.getNumOfSets() +"");
        d1.setVisibility(View.INVISIBLE);
        d2.setVisibility(View.INVISIBLE);
        d3.setVisibility(View.INVISIBLE);

        if (relatedWorkout.getIntensity() == 1) {
            d1.setVisibility(View.VISIBLE);
        }else if (relatedWorkout.getIntensity() == 2) {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
        }else {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
        }
    }
}