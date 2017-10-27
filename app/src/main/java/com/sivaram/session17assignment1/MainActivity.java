package com.sivaram.session17assignment1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Create Start And Stop Music Button Objects.
    Button startPlayButton, stopPlayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Start And Stop Music Buttons
        startPlayButton = (Button) findViewById(R.id.startPlayButton);
        stopPlayButton = (Button) findViewById(R.id.stopPlayButton);

        // Set Start Play Button On CLick Listener
        startPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create An intent to start MusicPlayer Service.
                Intent intent = new Intent(MainActivity.this,MusicPlayerService.class);

                // Start Music Player Service.
                startService(intent);

                // Send Notification on Service Start.
                showNotification();
            }
        });

        // Set Stop Music OnClick Listener
        stopPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create And Intent for MusicPlayerService.
                Intent intent = new Intent(MainActivity.this,MusicPlayerService.class);

                // Stop Service.
                stopService(intent);
            }
        });
    }

    public void showNotification(){
        // Create Notification Compact Builder Object for current context.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // Set Required Values to Notification Compact Builder Object
        builder.setSmallIcon(R.mipmap.ic_launcher) // Icon
                .setContentTitle("Service Running") // Notification Title
                .setContentText("Music Playing") // Content Text
                .setOngoing(true); // On Going

        // Create An Intent To Set Which Activity Shall start on
        Intent startIntent = new Intent(this, MainActivity.class);
        // Set Pending Intent for Notification Action.
        PendingIntent contentIntent = PendingIntent.getActivity(this, 101, startIntent, 0);

        // Set Content Intent To Notification Compact Builder.
        builder.setContentIntent(contentIntent);

        // Create Object of Notification with Notification Builder.
        Notification notification = builder.build();

        // Create Notification manager to Request Notification Service.
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Send Notification.
        notificationManager.notify(101, notification);
    }
}
