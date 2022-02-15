package com.itay.finalproject.ui.activities;

import static com.itay.finalproject.DBManager.currentUser;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.itay.finalproject.DBManager;
import com.itay.finalproject.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        EditText heightEt,widthEt;
        Button saveBtn;
        heightEt = findViewById(R.id.height_settings);
        widthEt = findViewById(R.id.width_settings);
        saveBtn = findViewById(R.id.save_settings_btn);
        saveBtn.setOnClickListener(v -> {
            if(heightEt.getText().toString().isEmpty() || widthEt.getText().toString().isEmpty()) {
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                return;
            }
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Final Project");
            dialog.setMessage("Saving settings...");
            dialog.show();
            currentUser.setHeight(Float.parseFloat(heightEt.getText().toString()));
            currentUser.setWeight(Float.parseFloat(widthEt.getText().toString()));
            DBManager.saveUser(unused -> {
            dialog.dismiss();
            Toast.makeText(getApplicationContext(),"Successfully Saved Settings",Toast.LENGTH_SHORT).show();
            finish();
            }, e -> {
                Toast.makeText(this,"There was a problem saving settings",Toast.LENGTH_SHORT).show();
             dialog.dismiss();
            });
        });


        findViewById(R.id.back_buttun_settings).setOnClickListener((v) -> finish());
    }

}
