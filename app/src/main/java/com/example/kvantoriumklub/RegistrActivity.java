package com.example.kvantoriumklub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrActivity extends AppCompatActivity {

    EditText email, password, name, surname, lesson;
    Button teacher, student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);
        student = findViewById(R.id.student);
        email = findViewById(R.id.email_ET);
        password = findViewById(R.id.password_ET);
        name = findViewById(R.id.name_ET);
        surname = findViewById(R.id.surname_ET);
        lesson = findViewById(R.id.lesson_ET);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) Toast.makeText(RegistrActivity.this,"Есть пустые поля!", Toast.LENGTH_SHORT).show();
                else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                HashMap<String,String> userInfo = new HashMap<>();
                                userInfo.put("name", name.getText().toString());
                                userInfo.put("surname", surname.getText().toString());
                                FirebaseDatabase.getInstance().getReference().child("Users").child(lesson.getText().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            startActivity(new Intent(RegistrActivity.this, Page.class));
                                            overridePendingTransition(R.anim.slidein, R.anim.slideout);
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }
}