package com.gdunn.owner.recipefeed;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class singleRecipe extends AppCompatActivity {
    private String intentURL;
    public static String WEB_ADDRESS = "web_address";
    public recipeModel model;
    public int test;
    public View mainView;
    RecyclerView recipeRecycler;
    List<recipeContent> ingredients_directions;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipelayout);
        Intent intent = getIntent();
        intentURL = intent.getStringExtra(WEB_ADDRESS);
        new recipeLoader().execute();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }
    private class recipeLoader extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                //set up new recipe instance
                model = new recipeModel();
                //set up new list of ingredients/directions
                ingredients_directions = new ArrayList<>();
                //retrieve html document
                Document mRecipePage = Jsoup.connect(intentURL).get();
                //Two different page layouts currently on website, this will allow jSoup to read the alternate layout
                Elements pageType = mRecipePage.select("body[ng-app=allrecipes]");
                if(pageType.size() >0)
                {
                    //select and set recipe title
                    String title = mRecipePage.select("h1#recipe-main-content").text();
                    model.setTitle(title);
                    //select and set recipe image
                    Elements imageElement = mRecipePage.select("img[class=rec-photo]");
                    model.setRecipeImage(imageElement.attr("src"));
                    //select and set author name
                    String authorName = mRecipePage.select("span[class=submitter__name]").text();
                    model.setAuthor(authorName);
                    //select and set author image source
                    String authorImageURL = mRecipePage.select("img[class=img-profile--submitter]").attr("src");
                    model.setAuthorImage(authorImageURL);
                    //select and set description
                    String description = mRecipePage.select("div[class=submitter__description]").text();
                    model.setDescription(description);

                    //collect all the ingredient uls into one list of li
                    List<Element> ingredients = mRecipePage.select("ul.checklist > li > label > span[itemprop=recipeIngredient]");
                    for(int i=0; i<ingredients.size(); i++){
                        if(i==0)
                        {
                            recipeContent headerItem = new recipeContent();
                            headerItem.setType(recipeContent.TITLE_TYPE);
                            headerItem.setCollectedContent("Ingredients");
                            ingredients_directions.add(headerItem);
                        }
                        Element currentItem = ingredients.get(i);
                        recipeContent ingredientItem = new recipeContent();
                        ingredientItem.setType(recipeContent.INGREDIENTS_TYPE);
                        ingredientItem.setCollectedContent(currentItem.text());
                        ingredients_directions.add(ingredientItem);
                    }
                    List<Element> directions = mRecipePage.select("ol[itemprop=recipeInstructions] > li");
                    for(int i=0; i<directions.size();i++)
                    {
                        if(i==0)
                        {
                            recipeContent headerItem = new recipeContent();
                            headerItem.setType(recipeContent.TITLE_TYPE);
                            headerItem.setCollectedContent("Directions");
                            ingredients_directions.add(headerItem);
                        }
                        Element currentItem = directions.get(i);
                        recipeContent directionItem = new recipeContent();
                        directionItem.setType(recipeContent.DIRECTIONS_TYPE);
                        directionItem.setCollectedContent(currentItem.text());
                        ingredients_directions.add(directionItem);
                    }
                }else
                {
                    //select and set recipe title
                    Elements titleElement = mRecipePage.select("head > meta[property=og:title]");
                    String title = titleElement.attr("content");
                    model.setTitle(title);
                    //select and set recipe image
                    Elements imageElement = mRecipePage.select("head > meta[name=pinterest:media]");
                    model.setRecipeImage(imageElement.attr("content"));
                    //select and set author name
                    String authorName = mRecipePage.select("span[class=author-name-title]").text();
                    model.setAuthor(authorName);
                    //select and set author image source
                    Elements authorImageURLElement = mRecipePage.select("div[class=author-text] > img");
                    String authorImageURL = authorImageURLElement.attr("src");
                    model.setAuthorImage(authorImageURL);
                    //select and set description
                    String description = mRecipePage.select("div[class=submitter__description]").text();
                    model.setDescription(description);

                    //collect all the ingredient uls into one list of li
                    List<Element> ingredients = mRecipePage.select("ul.checklist > li > label > span[itemprop=recipeIngredient]");
                    for(int i=0; i<ingredients.size(); i++){
                        if(i==0)
                        {
                            recipeContent headerItem = new recipeContent();
                            headerItem.setType(recipeContent.TITLE_TYPE);
                            headerItem.setCollectedContent("Ingredients");
                            ingredients_directions.add(headerItem);
                        }
                        Element currentItem = ingredients.get(i);
                        recipeContent ingredientItem = new recipeContent();
                        ingredientItem.setType(recipeContent.INGREDIENTS_TYPE);
                        ingredientItem.setCollectedContent(currentItem.text());
                        ingredients_directions.add(ingredientItem);
                    }
                    List<Element> directions = mRecipePage.select("ol[itemprop=recipeInstructions] > li");
                    for(int i=0; i<directions.size();i++)
                    {
                        if(i==0)
                        {
                            recipeContent headerItem = new recipeContent();
                            headerItem.setType(recipeContent.TITLE_TYPE);
                            headerItem.setCollectedContent("Directions");
                            ingredients_directions.add(headerItem);
                        }
                        Element currentItem = directions.get(i);
                        recipeContent directionItem = new recipeContent();
                        directionItem.setType(recipeContent.DIRECTIONS_TYPE);
                        directionItem.setCollectedContent(currentItem.text());
                        ingredients_directions.add(directionItem);
                    }
                }



            }catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            layoutLoader();
            recipeRecycler = mainView.findViewById(R.id.recipe_recycler);
            DoubleListAdapter adapter = new DoubleListAdapter(getApplicationContext(), ingredients_directions);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recipeRecycler.setLayoutManager(mLayoutManager);
            recipeRecycler.setAdapter(adapter);
            super.onPostExecute(aVoid);
        }
    }
    //Method to load layout with content from the model
    private void layoutLoader()
    {
        mainView = LayoutInflater.from(this).inflate(R.layout.recipelayout,null);
        //Set toolbar title
        CollapsingToolbarLayout toolbarLayout = mainView.findViewById(R.id.collapsingToolbar);
        toolbarLayout.setTitle(model.getTitle());
        //Set recipe image
        ImageView recipeImage = mainView.findViewById(R.id.recipe_image);
        if(!model.getRecipeImage().isEmpty())
        {
            Picasso.with(this).load(model.getRecipeImage()).into(recipeImage);
        }

        setContentView(mainView);
    }
}
