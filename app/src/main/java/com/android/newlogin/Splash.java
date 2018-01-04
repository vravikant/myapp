package com.android.newlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class Splash extends AppCompatActivity {
    LinearLayout l1, l2 ;
    Button b2;
    Animation a1 ,a2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        b2 = (Button)findViewById(R.id.button2) ;
        l1 = (LinearLayout)findViewById(R.id.l1);
        l2 = (LinearLayout)findViewById(R.id.l2);
        a1 = AnimationUtils.loadAnimation(this ,R.anim.upward);
        a2 = AnimationUtils.loadAnimation(this , R.anim.downward);
        l1.setAnimation(a1);
        l2.setAnimation(a2);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Splash.this , MainActivity.class));
                finish();
            }
        });
    }
}
