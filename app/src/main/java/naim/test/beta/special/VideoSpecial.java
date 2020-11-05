package naim.test.beta.special;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import naim.test.beta.R;

public class VideoSpecial extends AppCompatActivity {
    VideoView video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_special);
        video=findViewById(R.id.video);
        Intent intent=getIntent();

        int videoRecup=intent.getIntExtra("Video",R.raw.videotest);


     String videopath="android.resource://"+getPackageName()+"/" + videoRecup;
        Uri uri=Uri.parse(videopath);
        video.setVideoURI(uri);



        MediaController mediaController =new MediaController(this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);
        video.start();


    }
}