package com.example.todo.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todo.R;
import com.google.firebase.auth.FirebaseAuth;

public class logoutActivity extends AppCompatActivity {
    Button btnSignOut;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        fAuth= FirebaseAuth.getInstance();
        btnSignOut =findViewById(R.id.btnSignOut);

        Intent myIntent = getIntent();
        String string = myIntent.getStringExtra("message");

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                AlertDialog.Builder builder = new AlertDialog.Builder(logoutActivity.this);
                builder.setTitle("Confirmation PopUp!").setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
    }
}