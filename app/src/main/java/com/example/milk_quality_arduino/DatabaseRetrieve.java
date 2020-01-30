package com.example.milk_quality_arduino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DatabaseRetrieve extends AppCompatActivity {
    TextView DBlogout;
    FirebaseAuth mfirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_retrieve);
        DBlogout=(TextView)findViewById(R.id.dblogout);
        mfirebase=FirebaseAuth.getInstance();

        DBlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfirebase.signOut();
                finish();
                Intent dblogoutIntent= new Intent(DatabaseRetrieve.this,UserLogin.class);
                startActivity(dblogoutIntent);
            }
        });




    }
}
