package naim.test.beta.special;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import java.util.ArrayList;

import naim.test.beta.R;
import naim.test.beta.favori.Favori;
import naim.test.beta.recette.Model;
import naim.test.beta.recette.Recettes;
import naim.test.beta.recette.itemAdapter;

public class Special extends AppCompatActivity {
    ArrayList<ModelSpecial> itemlist = new ArrayList<>();
    RecyclerView recyclerView;
    itemAdapterSpecial itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_special);


        recyclerView=findViewById(R.id.recyclerviewrecettes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

       ArrayList<ModelSpecial> Donnee;
        Donnee=viewAll();

        itemAdapterSpecial itemAdapter=new itemAdapterSpecial(this,Donnee);
        recyclerView.setAdapter(itemAdapter);

    }

    public ArrayList<ModelSpecial> viewAll()
    {
        ArrayList<Integer> listImage=new ArrayList<Integer>();
        listImage.add(R.drawable.salade_de_macaroni);
        listImage.add(R.drawable.succes_aux_noisette);
        listImage.add(R.drawable.oeuf_farci);
        listImage.add(R.drawable.oeufs_espagnole);

        itemlist.add(new ModelSpecial("Salade",R.drawable.salade_de_tortellinis,R.raw.videotest,listImage
                ,"" +
                "– 400 ml de tomates concassées\n" +
                "– 8 petits oeufs\n" +
                "– 1 gros poivron rouge\n" +
                "– 1/2 poivron jaune\n" +
                "– 1 oignon jaune de 100 g environ\n" +
                "– 2 gousses d’ail\n" +
                "– 3 cuil. à soupe ou à table d’olives vertes en rondelles ou 30 g\n" +
                "– 1 cuil. à soupe ou à table d’huile d’olives\n" +
                "– 1 cuil. à soupe ou à table de concentré ou pâte de tomate\n" +
                "– 1 cuil. à soupe ou à table d’origan séché\n" +
                "– 1 cuil. à café ou à thé de paprika doux\n" +
                "– 1/2 cuil. à soupe ou à table de coriandre hachée\n" +
                "– 1/2 cuil. à café ou à thé de paprika fumé\n" +
                "– poivre au goût\n",
                "Etape: 1\n" +
                        "Epluchez l’oignon et taillez-le en dés.\n" +
                        "Etape: 2\n" +
                        "Lavez, épluchez et taillez les poivrons en dés.\n" +
                        "Etape: 3\n" +
                        "Dans une grande poêle, faites chauffer l’huile à feu moyen. Pendant qu’elle\n" +
                        "chauffe, épluchez l’ail et taillez-la en petits dés.\n" +
                        "Etape: 4\n" +
                        "Ajoutez l’oignon, les poivrons et l’ail et faites revenir à feu moyen pendant 2\n" +
                        "à 3 minutes jusqu’à ce que l’oignon devienne transparent.\n" +
                        "Etape: 5\n" +
                        "Ajoutez les tomates concassées, le concentré de tomates, le paprika, le\n" +
                        "paprika fumé, l’origan, Mélangez et faites mijoter 2 minutes.\n" +
                        "Etape: 6\n" +
                        "Ajoutez le poivre et les olives. Mélangez.\n" +
                        "Etape: 7\n" +
                        "Faites 4 trous dans la préparation pour y mettre les oeufs. Cassez 2 oeufs\n" +
                        "dans chaque trou.\n" +
                        "Etape: 8\n" +
                        "Couvrez la poêle et faites cuire 4 minutes. Enlevez le couvercle et cuisez\n" +
                        "encore 1 à 2 minutes selon la puissance de chauffe jusqu’à ce que le blanc\n" +
                        "soit coagulé.\n" +
                        "Etape: 9\n" +
                        "Saupoudrez de coriandre hachée et servez accompagné d’une céréale ou\n" +
                        "d’une pseudo céréale (quinoa, sarrasin)."
                ));


/*
            Toast.makeText(Special.this, "Il n'a aucune recette spéciale pour l'instant", Toast.LENGTH_LONG).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                onBackPressed();
            }
        }, 3000);

 */

        return itemlist;

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem itemMenu=menu.findItem(R.id.menu);

        itemMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent=new Intent(Special.this, naim.test.beta.Menu.class);
                startActivity(intent);

                return false;
            }
        });

      MenuItem item=menu.findItem(R.id.search);
      item.setVisible(false);
        androidx.appcompat.widget.SearchView searchView=(androidx.appcompat.widget.SearchView) item.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                itemAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

}