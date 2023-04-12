package com.example.tt_bssd5250_112;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class MoodActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    String path;
    public static final String Extra = "extra";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        ImageView imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String choice = intent.getStringExtra(MoodActivity.Extra);
        if(choice.equals("Blue")){
            findViewById(R.id.mood_layout).setBackgroundColor(Color.BLUE);
            path = "android.resource://" + this.getPackageName() + "/raw/melody";
            imageView.setImageResource(R.drawable.blue);
        } else if (choice.equals("Yellow")){
            findViewById(R.id.mood_layout).setBackgroundColor(Color.YELLOW);
            path = "android.resource://" + this.getPackageName() + "/raw/piano";
            imageView.setImageResource(R.drawable.yellow);
        } else {
            findViewById(R.id.mood_layout).setBackgroundColor(Color.GREEN);
            path = "android.resource://" + this.getPackageName() + "/raw/step";
            imageView.setImageResource(R.drawable.green);
        }

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        Uri uri = Uri.parse(path);

        try {
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}