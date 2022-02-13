package com.itay.finalproject.ui.fragments.auth;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.itay.finalproject.DBManager;
import com.itay.finalproject.OnFirestoreObjectListener;
import com.itay.finalproject.R;
import com.itay.finalproject.models.AppUser;
import com.itay.finalproject.models.Workout;
import com.itay.finalproject.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginFragment extends Fragment {
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            this::onSignInResult
    );
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.dumbell)
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
    }
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            assert user != null;
            ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Final Project");
            progressDialog.setMessage("Signing you in..");
            progressDialog.setCancelable(false);
            progressDialog.show();
            DBManager.checkExistingAndAssign(user.getUid(), new OnFirestoreObjectListener<AppUser>() {
                @Override
                public void onSuccess(AppUser existingAppUser) {
                    if(existingAppUser == null) {
                        AppUser newAppUser = new AppUser();
                        newAppUser.setId(user.getUid());
                        newAppUser.setEmail(user.getEmail());
                        newAppUser.setWeight(0);
                        newAppUser.setHeight(0);
                        ArrayList<Workout> workouts = new ArrayList<>();
                        workouts.add(new Workout("dummy","dummy","dummy",0,0,0));
                        newAppUser.setDiary(workouts);
                        DBManager.editUser(newAppUser, unused -> dismissAndMoveOn(), e -> {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(),"There was a problem signing you in",Toast.LENGTH_SHORT).show();
                        });
                    }else dismissAndMoveOn();

                }
                private void dismissAndMoveOn() {
                    progressDialog.dismiss();
                    Intent i = new Intent(getContext(), MainActivity.class);
                    startActivity(i);
                    if(getActivity()!=null)
                        getActivity().finish();
                }

                @Override
                public void onFailure(Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),"There was a problem signing you in",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(),"There was a problem signing you in",Toast.LENGTH_SHORT).show();
        }
    }
}