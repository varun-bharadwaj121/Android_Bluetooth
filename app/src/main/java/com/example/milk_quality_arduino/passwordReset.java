package com.example.milk_quality_arduino;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class passwordReset extends AppCompatActivity {


    TextView regEmail;
    Button reset;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);


        regEmail=(EditText)findViewById(R.id.registeredEmail);
        reset=(Button)findViewById(R.id.reset);
        firebaseAuth= FirebaseAuth.getInstance();


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail= regEmail.getText().toString().trim();

                  if(userEmail.equals(""))
                  {
                      Toast.makeText(passwordReset.this,"please enter the email id", Toast.LENGTH_SHORT).show();
                  }else
                  {
                      firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful())
                               {
                                   Toast.makeText(passwordReset.this,"successfully sent", Toast.LENGTH_SHORT).show();
                                   finish();
                                   startActivity( new Intent(passwordReset.this, UserLogin.class));
                               }else
                               {
                                   Toast.makeText(passwordReset.this,"Error in reset ", Toast.LENGTH_SHORT).show();
                               }

                          }
                      });
                  }

            }
        });
    }
}
