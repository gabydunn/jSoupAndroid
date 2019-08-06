package com.gdunn.owner.recipefeed;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class singleRecipe extends AppCompatActivity {
    private String intentURL;
    public static String WEB_ADDRESS = "web_address";
    public recipeModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipelayout);
        Intent intent = getIntent();
        intentURL = intent.getStringExtra(WEB_ADDRESS);
        new recipeLoader().execute();


    }
    private class recipeLoader extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                //set up new recipe instance
                model = new recipeModel();
                //set up new list of ingredients/directions
                List<recipeContent> ingredients_directions = new ArrayList<>();
                //retrieve html document
                Document mRecipePage = Jsoup.connect(intentURL).get();
                //select and set recipe title
                String title = mRecipePage.select("h1#recipe-main-content").text();
                model.setTitle(title);
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




            }catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
        }
    }
}
