package com.example.charlie.bullsgym;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.paperdb.Paper;

public class UserProfile extends AppCompatActivity {

    public static String UPDATE_URL;
    public static final String KEY_PASS="Password";
    public static final String KEY_GENDER="Gender";
    public static final String KEY_WEIGHT="Weight";

    ImageView UserImage;
    MaterialEditText Updated_Email,Updated_Pass,Updated_Weight;
    Button ChangeGym,SaveProfile;
    String Email,Password,Gender,Weight,BMI,GymName,Latitude,Longitude,ImageURL;
    TextView CurrentGym,CurrentGender;
    MaterialSpinner ProfileSpinner;
    ProgressBar mProgressBar;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        UserImage=findViewById(R.id.profile_Image);
        Updated_Email=findViewById(R.id.ProfileEmail);
        Updated_Pass=findViewById(R.id.ProfilePassword);
        ChangeGym=findViewById(R.id.Change_Gym_button);
        SaveProfile=findViewById(R.id.Save_profile_button);
        Updated_Weight=findViewById(R.id.ProfileWeight);
        CurrentGym=findViewById(R.id.ProfileGymLocation);
        CurrentGender=findViewById(R.id.ProfileGender);
        ProfileSpinner=findViewById(R.id.ProfileGenderSpinner);
        mProgressBar=findViewById(R.id.Profile_progressBar);




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

        Updated_Email.setText(Email);
        Updated_Email.setEnabled(false);
        Updated_Pass.setText(Password);
        Updated_Weight.setText(Weight);
        CurrentGym.setText(GymName);
        CurrentGender.setText("Gender: "+Gender);

        if(Gender.equals("Male")){
            ProfileSpinner.setItems("Male","Female");
        }else if(Gender.equals("Female")){
            ProfileSpinner.setItems("Female","Male");
        }
        ProfileSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Gender=item.toString();
            }
        });




        ChangeGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,MapsActivity.class));
            }
        });

        SaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        String fetch_Password,fetch_Weight;
        fetch_Password=Updated_Pass.getText().toString().trim();
        fetch_Weight=Updated_Weight.getText().toString().trim();
        int weightvalid=Integer.valueOf(fetch_Weight);
        if(fetch_Password.isEmpty()){
            Updated_Pass.setError("Password id Required");
        }else if(fetch_Weight.isEmpty()){
            Updated_Weight.setError("Weight is Required");
        }else if(weightvalid<=0){
            Updated_Weight.setError("Invalid Weight");
        }else{
            saveProfile();
        }
    }

    private void saveProfile() {

    }
}
