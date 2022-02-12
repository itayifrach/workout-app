package com.itay.finalproject.ui.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.itay.finalproject.R;
import com.itay.finalproject.WorkoutsRvAdapter;

import java.util.Random;

public class DiaryFragment extends Fragment {

    //private MaterialTextView changingstr;
    //private int i;
    private RecyclerView rvWorkouts;
    private WorkoutsRvAdapter rvWorkoutsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dairy, container, false);
    }

    private void findViews(View view) {
        rvWorkouts = view.findViewById(R.id.rvDiary);
        //changingstr=view.findViewById(R.id.changeingStr);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*i= new Random().nextInt(10);
        switch(i)
        {
            case 0:
                changingstr.setText("NO PAIN NO GAIN");
                break;
            case 1:
                changingstr.setText("Be the best version of yourself");
                break;
            case 2:
                changingstr.setText("WORK HARD PLAY HARD");
                break;
            case 3:
                changingstr.setText("GO BEAST MODE!");
                break;
            case 4:
                changingstr.setText("It's not about being the best,its about being better than you were yesterday");
                break;
            case 5:
                changingstr.setText("Hard work pays off!");
                break;
            case 6:
                changingstr.setText("GO HARD OR GO HOME!");
                break;
            case 7:
                changingstr.setText("It's a mental game");
                break;
            case 8:
                changingstr.setText("Always be the hardest workin person in the room");
                break;
            case 9:
                changingstr.setText("No more excuses!");
                break;
            case 10:
                changingstr.setText("NEVER GIVE UP ON YOURSELF");
               /*break;
}


        */}

    }
