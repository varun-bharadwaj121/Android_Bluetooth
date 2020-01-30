package com.example.milk_quality_arduino;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout item2,item1,item3;
    Button cont , cancel;
    Animation fade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cont= (Button)findViewById(R.id.btncontinue);
        cancel= (Button)findViewById(R.id.btncancel);
        item2=(LinearLayout)findViewById(R.id.item2);
        item3=(LinearLayout)findViewById(R.id.item3);
        item1=(LinearLayout)findViewById(R.id.item1);
        fade= AnimationUtils.loadAnimation(this,R.anim.animbtn);
        cont.setAlpha(0);
        cancel.setAlpha(0);

        item2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                item2.setBackground(getDrawable(R.drawable.bg_item_selected));
                item1.setBackground(getDrawable(R.drawable.bg_item));
                item3.setBackground(getDrawable(R.drawable.bg_item));
                cont.setAlpha(1);
                cancel.setAlpha(1);
                cont.startAnimation(fade);

                cont.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent aboutIntent = new Intent(MainActivity.this,UserLogin.class);
                        startActivity(aboutIntent);
                    }
                });


            }
        });
        item1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                item1.setBackground(getDrawable(R.drawable.bg_item_selected));
                item2.setBackground(getDrawable(R.drawable.bg_item));
                item3.setBackground(getDrawable(R.drawable.bg_item));
                cont.setAlpha(1);
                cancel.setAlpha(1);
                cont.startAnimation(fade);

                cont.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent aboutIntent = new Intent(MainActivity.this,About.class);
                        startActivity(aboutIntent);
                    }
                });
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                item3.setBackground(getDrawable(R.drawable.bg_item_selected));
                item2.setBackground(getDrawable(R.drawable.bg_item));
                item1.setBackground(getDrawable(R.drawable.bg_item));
                cont.setAlpha(1);
                cancel.setAlpha(1);
                cont.startAnimation(fade);
                cont.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent aboutIntent = new Intent(MainActivity.this,About.class);
                        startActivity(aboutIntent);
                    }
                });
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    item2.setBackground(getDrawable(R.drawable.bg_item));
                    item1.setBackground(getDrawable(R.drawable.bg_item));
                    item3.setBackground(getDrawable(R.drawable.bg_item));
                }
                cont.setAlpha(0);
                cancel.setAlpha(0);
            }
        });
    }}