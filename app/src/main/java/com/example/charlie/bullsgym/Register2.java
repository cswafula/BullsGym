package com.example.charlie.bullsgym;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class Register2 extends AppCompatActivity {
    private static final String TAG = "Register2";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
            init();
    }

    private void notification() {
        mNotificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Bulls Gym")
                .setContentText("Your need to turn on location to select gym")
                .setSmallIcon(R.drawable.ic_location_icon);

        Notification mynotification = notifyBuilder.build();
        mNotificationManager.notify(NOTIFICATION_ID,mynotification);
    }

    private void init() {
        Button SelectGym= (Button) findViewById(R.id.Select_Gym);
        SelectGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register2.this,SecondMapsActivity.class));
            }
        });
    }

//    public boolean versionCheck(){
//        Log.d(TAG, "versionCheck: checking google services version");
//        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Register2.this);
//        if(available == ConnectionResult.SUCCESS){
//            //everything is okay and the user can make requests
//            Log.d(TAG, "versionCheck: google play services is available");
//            return true;
//        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
//            //an error occurred but we can resolve it
//            Log.d(TAG, "versionCheck: an error occured but we can fix it");
//            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(Register2.this, available,ERROR_DIALOG_REQUEST);
//            dialog.show();
//        }else{
//            Toast.makeText(getApplicationContext(),"Your Google maps version is incomptible",Toast.LENGTH_LONG).show();
//        }
//        return false;
//    }
}
