package naim.test.beta.recette;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import naim.test.beta.Detail;
import naim.test.beta.R;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> implements Filterable {
    List<Model> itemlist;
    LayoutInflater layoutInflater;
    List<Model> itemlistFull;
    Context c;

/////////////////constructor////////////////
    public itemAdapter(Context context, ArrayList<Model> itemlist){
        this.itemlist=itemlist;
        this.itemlistFull=new ArrayList<>(itemlist);
        this.layoutInflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public itemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.rowitem,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final itemAdapter.ViewHolder holder, final int position) {

       holder.image.setImageResource(itemlist.get(position).getImage());
       holder.titre.setText(itemlist.get(position).getTitre());


       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String gTitle=itemlist.get(position).getTitre();
               int gImage=itemlist.get(position).getImage();
               String gIngredient=itemlist.get(position).getIngredent();
               String gPreparation=itemlist.get(position).getPreparation();
               int gFavori=itemlist.get(position).getFavori();



               Intent intent=new Intent(v.getContext(), Detail.class);
               intent.putExtra("Image",gImage);
               intent.putExtra("Titre",gTitle);
               intent.putExtra("Favori",gFavori);
               intent.putExtra("Ingredient",gIngredient);
               intent.putExtra("Preparation",gPreparation);

               ActivityOptionsCompat options= ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(),holder.image, ViewCompat.getTransitionName(holder.image));
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                   v.getContext().startActivity(intent,options.toBundle());
               }else  v.getContext().startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
   Filter exampleFilter=new Filter() {
       @Override
       protected FilterResults performFiltering(CharSequence constraint) {
           List<Model> filteredList=new ArrayList<>();
           if (constraint.toString().isEmpty())
           {
               filteredList.addAll(itemlistFull);
           }
           else
           {
               String filterPattern = constraint.toString().toLowerCase();
               for (Model item: itemlistFull)
               {
                   if (item.getTitre().toLowerCase().contains(filterPattern))
                       filteredList.add(item);
               }
           }
           FilterResults filterResults=new FilterResults();
           filterResults.values=filteredList;
           return filterResults;
       }

       @Override
       protected void publishResults(CharSequence constraint, FilterResults filterResults) {

           itemlist.clear();
           itemlist.addAll((Collection<? extends Model>) filterResults.values);
           notifyDataSetChanged();
       }
   };


    ////////////////////////////////Viewholder///////////////////////
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView titre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            titre=itemView.findViewById(R.id.titre);
        }
    }
}
