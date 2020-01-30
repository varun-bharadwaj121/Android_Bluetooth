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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText useremail,userpwd;
    Button register;
    TextView alreadyReg;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth= FirebaseAuth.getInstance();
        useremail= (EditText)findViewById(R.id.useremail);
        userpwd= (EditText)findViewById(R.id.userpwd);
        register= (Button)findViewById(R.id.registerbtn);
        alreadyReg= (TextView) findViewById(R.id.alreadyReg);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegister();
            }
        });

        alreadyReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,UserLogin.class);
                startActivity(intent);
            }
        });

    }
    private  void UserRegister()
    {

        String upwd= userpwd.getText().toString().trim();
        String uemail= useremail.getText().toString().trim();



        if (TextUtils.isEmpty(upwd)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(uemail)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(uemail,upwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                if (!task.isSuccessful()) {
                    Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(Register.this, BluetoothDiscover.class));
                    finish();
                }

            }
        });
    }

}
