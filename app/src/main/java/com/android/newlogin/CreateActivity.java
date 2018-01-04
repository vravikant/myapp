package com.android.newlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity {
    EditText Name;
    EditText Email;
    EditText Password ;
    CardView Create_ac;
    TextView uLogin ;
   // ImageButton but1;
   // ImageButton but2;
    FirebaseAuth firebaseAuth;
    ProgressDialog loadingBar;
    DatabaseReference Userdata ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateActivity.this , MainActivity.class));
                finish();
            }
        });



        Name = (EditText)findViewById(R.id.textView2);
        Email = (EditText)findViewById(R.id.textView3);
        Password = (EditText)findViewById(R.id.textView4);
        Create_ac = (CardView)findViewById(R.id.cardView2);
        uLogin = (TextView) findViewById(R.id.textView5);
      // but1 = (ImageButton)findViewById(R.id.imageButton);
      //  but2 = (ImageButton)findViewById(R.id.imageButton2);

        Userdata = FirebaseDatabase.getInstance().getReference("details");

        loadingBar = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();


        Create_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    loadingBar.setTitle("Registering");
                    loadingBar.setMessage("please wait.. ");
                    loadingBar.show();
                    String User_em = Email.getText().toString().trim();
                    String User_pa = Password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(User_em , User_pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                loadingBar.dismiss();
                                Toast.makeText(CreateActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CreateActivity.this, MainActivity.class));
                                finish();
                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(CreateActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        uLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateActivity.this, MainActivity.class));
                finish();


            }
        });
    }


    private boolean validate() {
        boolean res = false;

        String UName = Name.getText().toString();
        String Upass = Password.getText().toString();
        String email = Email.getText().toString();

        if (UName.isEmpty() || Upass.isEmpty() || email.isEmpty()) {
            Toast.makeText(CreateActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {

          /*  String id = Userdata.push().getKey();
            getdetails newdet = new getdetails(id , UName , email);

            Userdata.child(id).setValue(newdet);   */

            res = true;
        }
        return res;

    }

}

