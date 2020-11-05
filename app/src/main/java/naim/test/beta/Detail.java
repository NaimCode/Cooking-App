package naim.test.beta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import naim.test.beta.recette.Recettes;
import naim.test.beta.recette.itemAdapter;

public class Detail extends AppCompatActivity {
    TextView ingredient, preparation;
    Toolbar toolbar;
    FloatingActionButton floatFavori, floatShare;
    ImageView imageCollapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        floatFavori = findViewById(R.id.floatFavorite);
        floatShare = findViewById(R.id.floatShare);
        imageCollapse = findViewById(R.id.imagecollapse);
        ingredient = findViewById(R.id.ingredient);
        preparation = findViewById(R.id.preparation);
        toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();

        final String Titre = intent.getStringExtra("Titre");
        String Ingredient = intent.getStringExtra("Ingredient");
        String Preparation = intent.getStringExtra("Preparation");
        int Image = intent.getIntExtra("Image", R.drawable.i1);
        final int[] Favori = {intent.getIntExtra("Favori", 0)};

        if (Favori[0] == 0)
            floatFavori.setImageResource(R.drawable.ic_favoritex);
        else
            floatFavori.setImageResource(R.drawable.ic_favorite);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Titre);
        getSupportActionBar().setElevation(20);
        imageCollapse.setImageResource(Image);

        ingredient.setText(Ingredient);
        preparation.setText(Preparation);


        ///////////////SHARE/////////////////////////////////////////
        floatShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Les recettes de Bousta");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "www.technxt.net");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });
        ///////////////////FAVORI////////////////////////////////////
        floatFavori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Favori[0] == 0) {
                    boolean check = Menu.myDB.updateFav(Titre, 1);
                    if (check == true) {

                        floatFavori.setImageResource(R.drawable.ic_favorite);
                        Toast.makeText(Detail.this, "La recette a été ajoutée à Mes favoris", Toast.LENGTH_LONG).show();
                        Favori[0] = 1;
                    }
                } else {
                    boolean check = Menu.myDB.updateFav(Titre, 0);
                    if (check == true) {
                        floatFavori.setImageResource(R.drawable.ic_favoritex);
                        Toast.makeText(Detail.this, "La recette a été retirée de Mes favoris", Toast.LENGTH_LONG).show();
                        Favori[0] = 0;
                    }
                }
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }


}


