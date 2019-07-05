package com.gdunn.owner.recipefeed;

import android.content.Context;
import android.support.annotation.NonNull;
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
        Picasso.with(nContext).load(currentRecipe.getImageUri()).into(holder.recipeImage);
        holder.title.setText(currentRecipe.getTitle());
        holder.description.setText(currentRecipe.getDescription());

        //TODO: set up on click listener for launch full recipe activity
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            //Dummy on click
            @Override
            public void onClick(View v) {
                Toast.makeText(nContext, currentRecipe.getTitle(), Toast.LENGTH_LONG).show();
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
        RelativeLayout parentLayout;

        public NewViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipecard_image);
            title = itemView.findViewById(R.id.recipecard_title);
            description = itemView.findViewById(R.id.recipecard_description);
            parentLayout = itemView.findViewById(R.id.recipecard_card);
        }
    }

}
