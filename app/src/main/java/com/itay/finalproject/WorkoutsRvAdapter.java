package com.itay.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itay.finalproject.models.Workout;
import com.itay.finalproject.ui.fragments.main.WorkoutsItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WorkoutsRvAdapter extends RecyclerView.Adapter<WorkoutsRvAdapter.WorkoutsViewHolder> {

    private List<Workout> workouts;
    private final boolean addPage;
    private WorkoutsItemClickListener listener;
    public WorkoutsRvAdapter(List<Workout> workouts, boolean addPage, WorkoutsItemClickListener listener) {
        workouts.removeIf(Workout::isDummy);
        this.workouts = workouts;
        this.addPage = addPage;
        this.listener=listener;
    }
    @NonNull
    @Override
    public WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item,parent,false);
        return new WorkoutsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsViewHolder holder, int position) {
        Workout workout = workouts.get(position);
        holder.bind(workout,addPage);
       holder.itemView.setOnClickListener(v -> {
            listener.itemClicked(workout);
        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    static class WorkoutsViewHolder extends RecyclerView.ViewHolder {
        ImageView workoutIv;
        TextView workoutNameTv,workoutDateTv;

        ImageView d1,d2,d3;
        public WorkoutsViewHolder(@NonNull View itemView) {
            super(itemView);
            d1 = itemView.findViewById(R.id.intensity_d1);
            d2 = itemView.findViewById(R.id.intensity_d2);
            d3 = itemView.findViewById(R.id.intensity_d3);
            workoutIv = itemView.findViewById(R.id.workout_image);
            workoutNameTv = itemView.findViewById(R.id.workout_name_tv);
            workoutDateTv = itemView.findViewById(R.id.workout_date_tv);
        }

        public void bind(Workout workout,boolean addPage) {
            d1.setVisibility(View.INVISIBLE);
            d2.setVisibility(View.INVISIBLE);
            d3.setVisibility(View.INVISIBLE);
            workoutDateTv.setVisibility(View.INVISIBLE);

            if(addPage) {

                workoutDateTv.setVisibility(View.VISIBLE);
                workoutDateTv.setText(DateUtil.getDateString(workout.getAddedAt()));
            }
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
            workoutNameTv.setText(workout.getWorkoutName());

            Picasso.get()
                    .load(workout.getWorkoutImage())
                    .into(workoutIv);
        }
    }
}
