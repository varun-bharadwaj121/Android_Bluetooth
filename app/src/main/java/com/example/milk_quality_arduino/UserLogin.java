package com.example.milk_quality_arduino;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLogin extends AppCompatActivity {
    EditText user, password;
    Button login;
    FirebaseApp firebaseApp;
    TextView regHere, forgot_pwd;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mauthstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        firebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        user = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.upwd);
        forgot_pwd= (TextView) findViewById(R.id.forgotpwd);
        login = (Button) findViewById(R.id.login);
        regHere=(TextView)findViewById(R.id.regHere);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });


        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPwdIntent= new Intent(UserLogin.this, passwordReset.class);
                startActivity(forgotPwdIntent);
            }
        });
        regHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Regintent= new Intent(UserLogin.this, Register.class);
                startActivity(Regintent);
            }
        });


        mauthstate= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(UserLogin.this,BluetoothDiscover.class));
                }
            }
        };

    }
    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(mauthstate);
    }

    public void loginUser() {
        String email = user.getText().toString().trim();
        String pwd = password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(UserLogin.this, "please enter the email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(UserLogin.this, "please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(UserLogin.this,"succesful",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(UserLogin.this,"try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


}
