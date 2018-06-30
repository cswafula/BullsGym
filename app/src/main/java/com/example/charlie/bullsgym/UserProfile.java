package com.example.charlie.bullsgym;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import io.paperdb.Paper;

public class UserProfile extends AppCompatActivity {

    ImageView UserImage;
    TextView currentBmi;
    MaterialEditText Updated_Email,Updated_Pass;
    Button ChangeGym,SaveProfile;
    String Email,Password,Gender,Weight,BMI,GymName,Latitude,Longitude,ImageURL;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        UserImage=findViewById(R.id.profile_Image);
        currentBmi=findViewById(R.id.Profile_currentBMI);
        Updated_Email=findViewById(R.id.ProfileEmail);
        Updated_Pass=findViewById(R.id.ProfilePassword);
        ChangeGym=findViewById(R.id.Change_Gym_button);
        SaveProfile=findViewById(R.id.Save_profile_button);


        Paper.init(this);
        Email=Paper.book().read("UserEmail").toString();
        Password=Paper.book().read("UserPassword").toString();
        Gender=Paper.book().read("UserGender").toString();
        Weight=Paper.book().read("UserWeight").toString();
        BMI=Paper.book().read("UserBMI").toString();
        GymName=Paper.book().read("UserGymName").toString();
        Latitude=Paper.book().read("UserLatitude").toString();
        Longitude=Paper.book().read("Userlongitude").toString();
        ImageURL=Paper.book().read("UserImageUrl").toString();

        currentBmi.setText("Current BMI: "+BMI);
        Updated_Email.setText(Email);
        Updated_Pass.setText(Password);



    }
}
