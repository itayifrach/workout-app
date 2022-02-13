package com.itay.finalproject.ui.fragments.main;

import static com.itay.finalproject.DBManager.currentUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.itay.finalproject.R;

public class BMIFragment extends Fragment {
private TextView res;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_workouts,container,false);
    }
    private void findViews(View view) {
        res=view.findViewById(R.id.result_text);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        float hight= currentUser.getHeight();
        float weight= currentUser.getWeight();
        float bmi=weight/(hight*hight);

        //res.setText("your bmi result is :"+bmi);
    }


}