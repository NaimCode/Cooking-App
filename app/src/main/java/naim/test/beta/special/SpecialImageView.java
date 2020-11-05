package naim.test.beta.special;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import naim.test.beta.R;

public class SpecialImageView extends AppCompatActivity {

ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_image_view);
        image=findViewById(R.id.image);
        Intent intent=getIntent();

        int Image=intent.getIntExtra("Image",R.drawable.oeufs_espagnole);
        image.setImageResource(Image);
    }
}