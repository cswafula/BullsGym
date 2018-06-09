package com.example.charlie.bullsgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Loginpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        Button Login=(Button) findViewById(R.id.login_button);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        startActivity(new Intent(Loginpage.this,HomepageNavigation.class));
    }

    public void CreateAccount(View view){
        startActivity(new Intent(Loginpage.this,Register.class));
    }
}
