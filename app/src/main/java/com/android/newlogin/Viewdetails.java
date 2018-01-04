package com.android.newlogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by user on 03-01-18.
 */

public class Viewdetails extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase fdata;
    FirebaseAuth.AuthStateListener fevent;
    DatabaseReference dref;
    String userid;
    TextView t1, t2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        t1 = (TextView) findViewById(R.id.textView10);
        t2 = (TextView) findViewById(R.id.textView11);


        firebaseAuth = FirebaseAuth.getInstance();
        fdata = FirebaseDatabase.getInstance();
        dref = fdata.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userid = user.getUid();


        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void showData(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            userinfo uinfo = new userinfo();
            uinfo.setName(ds.child(userid).getValue(userinfo.class).getName());
            uinfo.setEm(ds.child(userid).getValue(userinfo.class).getEm());

            String s1 = uinfo.getName();
            String s2 = uinfo.getEm();

            t1.setText(s1);
            t2.setText(s2);

        }
    }
}



