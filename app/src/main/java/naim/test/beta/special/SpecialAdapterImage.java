package naim.test.beta.special;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import naim.test.beta.R;

public class SpecialAdapterImage extends RecyclerView.Adapter<SpecialAdapterImage.Viewholder> {
    ArrayList<Integer> list;
    Context context;

    public SpecialAdapterImage(Context context,ArrayList<Integer> list)
    {
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public SpecialAdapterImage.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitemspecial,parent,false);


     return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpecialAdapterImage.Viewholder holder, final int position) {
        holder.image.setImageResource(list.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int image=list.get(position);
                Intent intent=new Intent(v.getContext(),SpecialImageView.class);
                intent.putExtra("Image",image);

                ActivityOptionsCompat options= ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(),holder.image, ViewCompat.getTransitionName(holder.image));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.getContext().startActivity(intent,options.toBundle());
                }else  v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        ImageView image;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.imageSpecial);
        }
    }
}
