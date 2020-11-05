package naim.test.beta.recette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import naim.test.beta.R;

import static naim.test.beta.Menu.myDB;

public class Recettes extends AppCompatActivity {

    ArrayList<Model> itemlist = new ArrayList<>();
    RecyclerView recyclerView;
    itemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recettes);


        recyclerView=findViewById(R.id.recyclerviewrecettes);
       recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        ArrayList<Model> Donnee;
        Donnee=viewAll();
        itemAdapter=new itemAdapter(this,Donnee);
        recyclerView.setAdapter(itemAdapter);




    }

    public ArrayList<Model> viewAll()
    {
        Cursor res= myDB.getAllData();
        if (res.getCount()!=0) {
            while (res.moveToNext()) {
                int image = fromByteArray(res.getBlob(1));
                Model m=new Model(res.getString(0), image, res.getInt(2), res.getString(3), res.getString(4));
                itemlist.add(m);

            }


        }
        res.close();
        return itemlist;
    }
    int fromByteArray(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem itemMenu=menu.findItem(R.id.menu);
        itemMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


       Intent intent=new Intent(Recettes.this, naim.test.beta.Menu.class);
 startActivity(intent);



                return false;
            }
        });

        MenuItem item=menu.findItem(R.id.search);
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