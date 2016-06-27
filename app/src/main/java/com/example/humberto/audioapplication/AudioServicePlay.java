package com.example.humberto.audioapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Humberto on 25/06/2016.
 */
public class AudioServicePlay extends Service {

    private static final int NOTIFICATION_ID_PLAY = 2;
    private String outputFile;

    @Override
    public void onCreate() {
        super.onCreate();

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";


        final Intent notificationIntent = new Intent(getApplicationContext(),
                AudioActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        final Notification notification = new Notification.Builder(
                getApplicationContext())
                //.setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true).setContentTitle("Music Playing")
                .setContentText("Click to Access Music Player")
                .setContentIntent(pendingIntent).build();

        // Put this Service in a foreground state, so it won't
        // readily be killed by the system
        startForeground(NOTIFICATION_ID_PLAY, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MediaPlayer m = new MediaPlayer();
        try {
            m.setDataSource(outputFile);
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            m.prepare();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        m.start();

        Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();

        return START_NOT_STICKY;

    }

    // Can't bind to this Service
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }
}