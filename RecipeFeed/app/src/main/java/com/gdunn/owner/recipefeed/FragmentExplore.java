package com.gdunn.owner.recipefeed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class FragmentExplore extends Fragment {
    View view;
    public FragmentExplore(){}
    RecyclerView exploreRecycler;
    ProgressBar progressBar;
    ArrayList<RecipeCardModel> exploreRecipes;
    String recipeURL = "https://www.allrecipes.com/";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.explore_layout, container,false);
        exploreRecipes = new ArrayList<>();
       progressBar = view.findViewById(R.id.progressBar);
        exploreRecycler = view.findViewById(R.id.recycler_explore);
        new exploreLoader().execute();
        return view;
    }

    private class exploreLoader extends AsyncTask<Void,Void,Void>
    {

        int mElementSize;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();



        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document mMainPage = Jsoup.connect(recipeURL).get();
                Elements mElementDataSize = mMainPage.select("article[class=fixed-recipe-card]");
                mElementSize = mElementDataSize.size();
                for(int i=0; i<mElementSize;i++){
                    RecipeCardModel newCard = new RecipeCardModel();

                    //Title
                    Elements mElementTitle =mMainPage.select("span[class=fixed-recipe-card__title-link]").eq(i);
                    String mTitle =mElementTitle.text();
                    //Description
                    Elements mElementDescription =mMainPage.select("div[class=fixed-recipe-card__description]").eq(i);
                    String mDescription =mElementDescription.text();
                    //Image url
                    Elements mElementImage =mMainPage.select("img[class=fixed-recipe-card__img]").eq(i);
                    String imageURL =mElementImage.attr("data-original-src");
                    //Load card instance
                    newCard.setDescription(mDescription);
                    newCard.setImageUri(imageURL);
                    newCard.setTitle(mTitle);
                    newCard.setId(i);

                    exploreRecipes.add(newCard);
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            exploreRecycler.setHasFixedSize(true);
            ExploreRecyclerAdapter adapter = new ExploreRecyclerAdapter(getContext(), exploreRecipes);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            exploreRecycler.setLayoutManager(mLayoutManager);
            exploreRecycler.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
            super.onPostExecute(aVoid);
        }
    }

}
