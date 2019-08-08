package com.gdunn.owner.recipefeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DoubleListAdapter extends RecyclerView.Adapter {

    Context nContext;
    List<recipeContent> data;
    int total_types;
    int directionCount =1;

    //TODO: set up view holders to reflect their layouts
    public static class HeaderTypeViewHolder extends RecyclerView.ViewHolder{
        TextView headerText;
        ConstraintLayout layout;
        public HeaderTypeViewHolder(@NonNull View parent) {
            super(parent);
            this.headerText = parent.findViewById(R.id.textview_header);
            this.layout = parent.findViewById(R.id.layout_header);
        }
    }
    public static class IngredientsTypeViewHolder extends RecyclerView.ViewHolder{
        TextView ingredientText;
        ConstraintLayout layout;
        public IngredientsTypeViewHolder(@NonNull View parent) {
            super(parent);
            this.ingredientText = parent.findViewById(R.id.textview_ingredient);
            this.layout = parent.findViewById(R.id.layout_ingredient);
        }
    }
    public static class DirectionTypeViewHolder extends RecyclerView.ViewHolder{
        TextView directionText;
        TextView directionNumber;
        ConstraintLayout layout;

        public DirectionTypeViewHolder(@NonNull View parent) {
            super(parent);

            this.directionNumber = parent.findViewById(R.id.textview_directionNumber);
            this.directionText = parent.findViewById(R.id.textview_direction);
            this.layout = parent.findViewById(R.id.layout_direction);

        }
    }
    public DoubleListAdapter(Context context, List<recipeContent> contents)
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        recipeContent currentItem = data.get(position);

        if(currentItem!= null){
            switch (currentItem.getType()){
                case recipeContent.TITLE_TYPE:
                    ((HeaderTypeViewHolder) holder).headerText.setText(currentItem.getCollectedContent());
                    break;
                case recipeContent.INGREDIENTS_TYPE:
                    ((IngredientsTypeViewHolder) holder).ingredientText.setText(currentItem.getCollectedContent());

                    break;
                case recipeContent.DIRECTIONS_TYPE:
                    ((DirectionTypeViewHolder) holder).directionText.setText(currentItem.getCollectedContent());
                    ((DirectionTypeViewHolder) holder).directionNumber.setText(Integer.toString(directionCount)+".");
                    directionCount++;
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
