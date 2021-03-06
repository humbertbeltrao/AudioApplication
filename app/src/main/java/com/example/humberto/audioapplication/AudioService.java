package com.example.humberto.audioapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.audiocapture.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Humberto on 25/06/2016.
 */
public class AudioService extends Service {

    private static final int NOTIFICATION_ID = 1;
    private MediaRecorder mediaRecorder;
    private String outputFile = null;

    @Override
    public  void onCreate(){
        super.onCreate();




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
        startForeground(NOTIFICATION_ID, notification);



    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getApplicationContext(), "Speak now", Toast.LENGTH_SHORT).show();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+(simpleDateFormat.format(new Date())).toString()+".3gp";
        Toast.makeText(getApplicationContext(), outputFile, Toast.LENGTH_SHORT).show();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(outputFile);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(getApplicationContext(), outputFile, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (null != mediaRecorder){
            mediaRecorder.stop();
            mediaRecorder.release();


        }
    }

    // Can't bind to this Service
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }

}
