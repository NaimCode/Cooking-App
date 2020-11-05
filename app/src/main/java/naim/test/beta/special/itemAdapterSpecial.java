package naim.test.beta.special;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import naim.test.beta.recette.Model;

public class itemAdapterSpecial extends RecyclerView.Adapter<itemAdapterSpecial.ViewHolder> implements Filterable {
    List<ModelSpecial> itemlist;
    LayoutInflater layoutInflater;
    List<ModelSpecial> itemlistFull;

/////////////////constructor////////////////
    public itemAdapterSpecial(Context context, ArrayList<ModelSpecial> itemlist){
        this.itemlist=itemlist;
        this.itemlistFull=new ArrayList<>(itemlist);
        this.layoutInflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public itemAdapterSpecial.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.rowitem,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final itemAdapterSpecial.ViewHolder holder, final int position) {

       holder.image.setImageResource(itemlist.get(position).getImage());
       holder.titre.setText(itemlist.get(position).getTitre());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String gTitle=itemlist.get(position).getTitre();
               int gImage=itemlist.get(position).getImage();
               String gIngredient=itemlist.get(position).getIngredient();
               String gPreparation=itemlist.get(position).getPreparation();
               ArrayList<Integer> glist=itemlist.get(position).getImageS();
               int gvideo=itemlist.get(position).getVideoS();
            //////////il reste les imagesS et videoS///////////////////



               Intent intent=new Intent(v.getContext(), DetailSpecial.class);
               intent.putExtra("Image",gImage);
               intent.putExtra("Titre",gTitle);
               intent.putExtra("Ingredient",gIngredient);
               intent.putExtra("Preparation",gPreparation);
               intent.putExtra("ListImage",glist);
               intent.putExtra("Video",gvideo);

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
           List<ModelSpecial> filteredList=new ArrayList<>();
           if (constraint.toString().isEmpty())
           {
               filteredList.addAll(itemlistFull);
           }
           else
           {
               String filterPattern = constraint.toString().toLowerCase();
               for (ModelSpecial item: itemlistFull)
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
           itemlist.addAll((Collection<? extends ModelSpecial>) filterResults.values);
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
