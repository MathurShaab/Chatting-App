package com.example.jatinschattingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jatinschattingapp.databinding.ActivitySignupBinding;
import com.example.jatinschattingapp.model.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Signup extends AppCompatActivity {
ActivitySignupBinding binding;
FirebaseDatabase database;
private FirebaseAuth auth;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        auth =FirebaseAuth.getInstance();

progressDialog =new ProgressDialog(Signup.this);
progressDialog.setTitle("Creating Account In Jatin ' s App");
progressDialog.setMessage("Please Wait We Are Creating Your Account ");

binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword
                        (binding.etemail.getText().toString(), binding.etpass.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    user usr = new user
                                            (binding.etusername.getText().toString(),binding.etemail.getText().toString(),binding.etpass.getText().toString());
                               String id = task.getResult().getUser().getUid();
                               database.getReference("user").child(id).setValue(usr);
                                    Toast.makeText(Signup.this,"Successfully Registered In Jatin's App",Toast.LENGTH_SHORT).show();


                                }
                                else {
                                    Toast.makeText(Signup.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    Log.d("jatin",task.getException().getMessage());
                                }



                            }
                        });

            }

        });


        binding.tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Signup.this,Signin.class);
                startActivity(intent);

            }
        });


}
    }