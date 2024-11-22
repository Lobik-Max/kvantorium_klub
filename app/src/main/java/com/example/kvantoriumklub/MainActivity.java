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
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button login_Bt, registr_Bt;
    EditText login_ET, password_ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_Bt = findViewById(R.id.log_Bt);
        login_ET = findViewById(R.id.login_ET);
        password_ET = findViewById(R.id.password_ET);
        registr_Bt = findViewById(R.id.reg_Bt);
        login_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login_ET.getText().toString().isEmpty() || password_ET.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Есть пустые поля!",Toast.LENGTH_SHORT).show();
                }
                else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(login_ET.getText().toString(), password_ET.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(MainActivity.this, Page.class));
                                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                            }
                        }
                    });
                }
            }
        });
        registr_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrActivity.class));
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
            }
        });
    }
}