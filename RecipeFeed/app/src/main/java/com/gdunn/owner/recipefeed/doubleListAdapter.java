package com.gdunn.owner.recipefeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class doubleListAdapter extends RecyclerView.Adapter {

    Context nContext;
    List<recipeContent> data;
    int total_types;

    //TODO: set up view holders to reflect their layouts
    public static class HeaderTypeViewHolder extends RecyclerView.ViewHolder{
        public HeaderTypeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static class IngredientsTypeViewHolder extends RecyclerView.ViewHolder{

        public IngredientsTypeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static class DirectionTypeViewHolder extends RecyclerView.ViewHolder{

        public DirectionTypeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public doubleListAdapter(Context context, List<recipeContent> contents)
    {
        this.data = contents;
        this.nContext = context;
        total_types = data.size();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case recipeContent.TITLE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header, parent,false);
                return new HeaderTypeViewHolder(view);
            case recipeContent.DIRECTIONS_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_direction,parent,false);
                return new DirectionTypeViewHolder(view);
            case recipeContent.INGREDIENTS_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ingredient,parent,false);
                return new IngredientsTypeViewHolder(view);
        }
        return null;
    }

    //determine what kind  of item it is
    @Override
    public int getItemViewType(int position) {
        switch (data.get(position).getType())
        {
            case 0:
                return recipeContent.TITLE_TYPE;
            case 1:
                return recipeContent.INGREDIENTS_TYPE;
            case 2:
                return recipeContent.DIRECTIONS_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        recipeContent currentItem = data.get(position);

        if(currentItem!= null){
            switch (currentItem.getType()){
                case recipeContent.TITLE_TYPE:
                    break;
                case recipeContent.INGREDIENTS_TYPE:
                    break;
                case recipeContent.DIRECTIONS_TYPE:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
