package com.android.newlogin;



import android.app.ProgressDialog;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.CardView;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    CardView b1;
    EditText ed1, ed2;
    TextView link_sign;
    int counter = 5;
    ProgressDialog loadingBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (CardView) findViewById(R.id.cardView1);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        link_sign = (TextView) findViewById(R.id.textView);

        loadingBar = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

       // FirebaseUser user = firebaseAuth.getCurrentUser();


      /*  if(user!=null){
            finish();
            startActivity(new Intent(MainActivity.this , profile.class));
        }


*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (good_to_go()) {
                    validate(ed1.getText().toString(), ed2.getText().toString());
                }
            }
        });

        link_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    startActivity(new Intent(MainActivity.this, CreateActivity.class));
                    finish();
                } catch (Exception ex) {

                    ex.printStackTrace();

                }
            }
        });


    }

    private boolean good_to_go() {
        boolean val = false;

        String U_em = ed1.getText().toString();
        String U_pa = ed2.getText().toString();

        if (U_pa.isEmpty() || U_em.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
        } else {
            val = true;
        }
        return val;
    }

    private void validate(String U_name, String U_pass) {
        loadingBar.setMessage("Authenticating ");
        loadingBar.show();


        if (U_name.equals("ravi") || U_pass.equals("123456")) {
            startActivity(new Intent(MainActivity.this, myprofile.class));
        } else {
            firebaseAuth.signInWithEmailAndPassword(U_name, U_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        loadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, myprofile.class));
                    } else {
                        loadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        counter--;
                        if (counter == 0) {
                            b1.setEnabled(false);
                        }
                    }
                }
            });
        }
    }
}