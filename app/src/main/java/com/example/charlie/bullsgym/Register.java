package com.example.charlie.bullsgym;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import io.paperdb.Paper;

public class Register extends AppCompatActivity {
MaterialEditText Email,Password,Confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Email= findViewById(R.id.RegisterEmail);
        Password=findViewById(R.id.RegisterPassword);
        Confirm=findViewById(R.id.RegisterConfirmPassword);

        Paper.init(this);

        Button Register = (Button) findViewById(R.id.Continue_button);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
    }

    private void validation() {
        String Emailaddress,Reg_Pass,Reg_Confirm;
        Emailaddress=Email.getText().toString().trim();
        Reg_Pass=Password.getText().toString().trim();
        Reg_Confirm=Confirm.getText().toString().trim();

        if(Emailaddress.isEmpty()){
            Email.setError("Email address is Required");
        }else if(Reg_Pass.isEmpty()){
            Password.setError("Password is Required");
        }
        else if(Reg_Confirm.isEmpty()){
            Confirm.setError("Confirmation is Required");
        }else if(!Reg_Pass.equals(Reg_Confirm)){
            Confirm.setError("Passwords don't match");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(Emailaddress).matches()){
            Email.setError("Please enter a valid Email address");
        }else{
            Paper.book().write("Email",Emailaddress);
            Paper.book().write("Password",Reg_Pass);
            startActivity(new Intent(Register.this, Register2.class));
            finish();
        }

    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(Register.this);
        builder.setMessage("Are you sure you want to go back, you're almost done");
        builder.setCancelable(true);
        builder.setNegativeButton("No, Stay!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes, Close!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Register.this, Loginpage.class));
                finish();
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
}
