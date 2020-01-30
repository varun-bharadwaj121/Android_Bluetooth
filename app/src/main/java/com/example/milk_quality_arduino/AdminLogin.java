package com.example.milk_quality_arduino;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
    EditText DBname, DBpwd;
    Button DBlogin;
    FirebaseAuth mfirebase;
    FirebaseApp firebaseApp1;
    private FirebaseAuth.AuthStateListener dauthstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        mfirebase= FirebaseAuth.getInstance();
        firebaseApp1.initializeApp(this);

        DBname=(EditText)findViewById(R.id.Dbuname);
        DBpwd=(EditText)findViewById(R.id.Dbupwd);
        DBlogin=(Button)findViewById(R.id.Dblogin);

        DBlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBlog();
            }
        });
        dauthstate= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    startActivity(new Intent(AdminLogin.this,DatabaseRetrieve.class));
                }
            }
        };

    }
    @Override
    protected void onStart() {
        super.onStart();

        mfirebase.addAuthStateListener(dauthstate);
    }

    void DBlog()
    {
        String email =DBname .getText().toString().trim();
        String pwd = DBpwd.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(AdminLogin.this, "please enter the email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(AdminLogin.this, "please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }


        mfirebase.signInWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(AdminLogin.this,"succesful",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(AdminLogin.this,"try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
