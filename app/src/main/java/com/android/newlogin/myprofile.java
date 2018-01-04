package com.android.newlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class myprofile extends AppCompatActivity {
     // Button but3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

     /*   but3 = (Button)findViewById(R.id.button3);

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(myprofile.this , Viewdetails.class));
            }
        });  */
    }
}
