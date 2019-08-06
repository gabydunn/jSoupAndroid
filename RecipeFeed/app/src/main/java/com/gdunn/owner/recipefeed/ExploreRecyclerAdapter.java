package com.gdunn.owner.recipefeed;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExploreRecyclerAdapter extends RecyclerView.Adapter<ExploreRecyclerAdapter.NewViewHolder> {
    Context nContext;
    List<RecipeCardModel> recipeCardList;

    public ExploreRecyclerAdapter(Context context, List<RecipeCardModel> recipes){
        nContext = context;
        recipeCardList = recipes;
    }

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_recipecard, parent, false);
        NewViewHolder holder = new NewViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewHolder holder, int i) {
        final RecipeCardModel currentRecipe = recipeCardList.get(i);
        holder.title.setText(currentRecipe.getTitle());
        holder.description.setText(currentRecipe.getDescription());
       Picasso.with(nContext).load(currentRecipe.getImageUri()).into(holder.recipeImage);
       Picasso.with(nContext).load(currentRecipe.getAuthorImage()).into(holder.authorImage);
       holder.authorname.setText(currentRecipe.getAuthorName());

        //TODO: set up on click listener for launch full recipe activity
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            //Dummy on click
            @Override
            public void onClick(View v) {
                //temporary webview for testing
//               Intent intent =new Intent(nContext, webview_activity.class);
//               intent.putExtra(singleRecipe.WEB_ADDRESS, currentRecipe.getAssociatedURL());
//               nContext.startActivity(intent);
               //setup for when adapter is done
               Intent intent = new Intent(nContext, singleRecipe.class);
               intent.putExtra(singleRecipe.WEB_ADDRESS, currentRecipe.getAssociatedURL());
               nContext.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeCardList.size();
    }

    public class NewViewHolder extends RecyclerView.ViewHolder{
        ImageView recipeImage;
        TextView title;
        TextView description;
        TextView authorname;
        CircleImageView authorImage;
        RelativeLayout parentLayout;

        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipecard_image);
            title = itemView.findViewById(R.id.recipecard_title);
            description = itemView.findViewById(R.id.recipecard_description);
            authorImage = itemView.findViewById(R.id.recipecard_authorimage);
            authorname = itemView.findViewById(R.id.recipecard_authorname);
            parentLayout = itemView.findViewById(R.id.recipecard_card);
        }
    }

}
