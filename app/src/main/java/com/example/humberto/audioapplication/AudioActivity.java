package com.example.humberto.audioapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import android.os.Bundle;
import android.os.Environment;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.audiocapture.R;
import com.example.humberto.audioapplication.AudioActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class AudioActivity extends Activity {
    private File root;
    private ArrayList<File> fileList = new ArrayList<>();
    private LinearLayout view;
    private TextView textView;
    Button play,stop, record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        getFiles();

        final Intent recordServiceIntent = new Intent(
                getApplicationContext(),
                AudioService.class);
        final Intent playServiceIntent = new Intent(
                getApplicationContext(),
                AudioServicePlay.class);


        play=(Button) findViewById(R.id.btn_play);
        stop=(Button)findViewById(R.id.btn_stop);
        record=(Button)findViewById(R.id.btn_record);

        stop.setEnabled(false);
        play.setEnabled(false);
        //outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

//        myAudioRecorder= new MediaRecorder();
//        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
//        myAudioRecorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                try {
//                    myAudioRecorder.prepare();
//                    myAudioRecorder.start();
//
//
//                }
//
//                catch (IllegalStateException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                record.setEnabled(false);
//                stop.setEnabled(true);
//
//                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
                startService(recordServiceIntent);
                stop.setEnabled(true);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myAudioRecorder.stop();
//                myAudioRecorder.release();
//                myAudioRecorder  = null;
//
//                stop.setEnabled(false);
//                play.setEnabled(true);
//
//                Toast.makeText(getApplicationContext(), "Audio recorded successfully",Toast.LENGTH_LONG).show();
                stopService(recordServiceIntent);
                Toast.makeText(getApplicationContext(), "Stop pressed",Toast.LENGTH_LONG).show();
               // view.removeAllViews();
                getFiles();
                play.setEnabled(true);



            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException,SecurityException,IllegalStateException {
//                MediaPlayer m = new MediaPlayer();
//
//                try {
//                    m.setDataSource(outputFile);
//                }
//
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    m.prepare();
//                }
//
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                m.start();
//
//                Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
                record.setEnabled(false);
                stop.setEnabled(false);
                startService(playServiceIntent);
            }
        });

    }//close onCreate method

    public void getFiles() {
        view = (LinearLayout) findViewById(R.id.view);

        //getting SDcard root path
        root = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath());
        getFile(root);
        textView = new TextView(this);
        for (int i = 0; i < fileList.size(); i++) {

            if(fileList.size() >= 1) {
                textView.setText(fileList.get(fileList.size()-1).getName());
            } else {
                textView.setText(fileList.get(i).getName());
            }

            textView.setPadding(5, 5, 5, 5);

            System.out.println(fileList.get(i).getName());

//            if (fileList.get(i).isDirectory()) {
//                textView.setTextColor(Color.parseColor("#FF0000"));
//            }

        }
        view.addView(textView);
    }
    public ArrayList<File> getFile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {


                    if (listFile[i].getName().endsWith(".3gp")) {
                        fileList.add(listFile[i]);
                    }


            }
        }
        return fileList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_audio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}