package com.example.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("mych", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Bitmap pic = BitmapFactory.decodeResource(getResources(),R.drawable.p);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "mych")
                .setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle("Hello Everyone!!!")
                .setContentText("Been a traveler my Whole life. Here are my top 5 destinations.")
                .setColor(Color.GREEN)
                .setLargeIcon(pic)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(pic)
                        .bigLargeIcon(null));

        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    public void showNotification(View view) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(1, notification);

    }

}