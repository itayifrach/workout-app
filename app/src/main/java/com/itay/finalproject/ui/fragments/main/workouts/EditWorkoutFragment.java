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

import com.itay.finalproject.R;

public class EditWorkoutFragment extends Fragment {


    private ImageView d1,d2,d3;
    private EditText repsEt,setsEt;
    private Button saveWorkoutBtn,deleteWorkoutBtn;

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
    }
}