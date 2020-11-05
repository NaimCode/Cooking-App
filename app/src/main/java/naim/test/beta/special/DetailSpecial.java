package naim.test.beta.special;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import naim.test.beta.R;

import static naim.test.beta.R.id.recyclerImage;
import static naim.test.beta.R.id.video;

public class DetailSpecial extends AppCompatActivity {
   RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
   SpecialAdapterImage specialAdapterImage;
   Toolbar toolbar;
   ImageView imageCollapse;
    TextView ingredient,preparation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_special);

        recyclerView=findViewById(recyclerImage);
        floatingActionButton=findViewById(R.id.floatvideo);
        toolbar=findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        imageCollapse=findViewById(R.id.imagecollapse);
        ingredient=findViewById(R.id.ingredient);
        preparation=findViewById(R.id.preparation);


        Intent intent=getIntent();

        final String Titre=intent.getStringExtra("Titre");
        String Ingredient=intent.getStringExtra("Ingredient");
        String Preparation=intent.getStringExtra("Preparation");
        final int video =intent.getIntExtra("Video",R.raw.videotest);
        ArrayList<Integer> listImage=intent.getIntegerArrayListExtra("ListImage");
        int Image = intent.getIntExtra("Image",R.drawable.i1);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Titre);
        getSupportActionBar().setElevation(20);
        imageCollapse.setImageResource(Image);

        ingredient.setText(Ingredient);
        preparation.setText(Preparation);




        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DetailSpecial.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        specialAdapterImage=new SpecialAdapterImage(DetailSpecial.this,listImage);
        recyclerView.setAdapter(specialAdapterImage);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DetailSpecial.this,VideoSpecial.class);
                intent.putExtra("Video",video);
                  startActivity(intent);

            }
        });


    }
}