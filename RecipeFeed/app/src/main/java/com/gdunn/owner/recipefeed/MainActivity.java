package com.gdunn.owner.recipefeed;

import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private String recipeURL = "https://www.allrecipes.com/";
    private AppBarLayout appBarLayout;
    private LinearLayout layout;
    private ProgressBar progressBar;
    private ViewPager viewPager;
    private ArrayList<RecipeCardModel> exploreRecipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout_id);
        appBarLayout = findViewById(R.id.appbarid);
        viewPager = findViewById(R.id.viewPager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Add fragments
        adapter.AddFragment(new FragmentExplore(), "Explore");
        adapter.AddFragment(new Fragmentviewpager(), "Saved");
        adapter.AddFragment(new FragmentYour(), "Your recipes");
        //Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //new exploreLoader().execute();
    }

    private class exploreLoader extends AsyncTask<Void, Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = new ProgressBar(MainActivity.this, null,android.R.attr.progressBarStyleLarge);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            layout.addView(progressBar,params);
            progressBar.setVisibility(View.VISIBLE);  //To show ProgressBar
            progressBar.setVisibility(View.GONE);     // To Hide ProgressBar
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document mMainPage = Jsoup.connect(recipeURL).get();
                Elements mElementsDataSize =mMainPage.select("article[class=fixed-recipe-card]");
                int mElementSize =mElementsDataSize.size();
                for(int i=0; i<mElementSize; i++)
                {
                    //Create new card instance
                    RecipeCardModel newCard = new RecipeCardModel();
                    //Title
                    Elements mElementTitle =mMainPage.select("span[class=fixed-recipe-card__title-link]").eq(i);
                    String mTitle =mElementTitle.text();
                    //Description
                    Elements mElementDescription =mMainPage.select("div[class=fixed-recipe-card__description ng-isolate-scope]").eq(i);
                    String mDescription =mElementDescription.text();
                    //Image url
                    Elements mElementImage =mMainPage.select("img[class=fixed-recipe-card__img ng-isolate-scope]").eq(i);
                    String imageURL =mElementImage.attr("src");
                    //Load card instance
                    newCard.setDescription(mDescription);
                    newCard.setImageUri(imageURL);
                    newCard.setTitle(mTitle);
                    newCard.setId(i);

                    exploreRecipes.add(newCard);

                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //handle to recycler layout
            RecyclerView mExplorRecyclerView =findViewById(R.id.recycler_explore);
            //performance improvement
            mExplorRecyclerView.setHasFixedSize(true);
            //instantiate adapter
            ExploreRecyclerAdapter eAdapter =new ExploreRecyclerAdapter(MainActivity.this,exploreRecipes);
            //layout manager set up
            RecyclerView.LayoutManager mLayoutManager =new LinearLayoutManager(getApplicationContext());
            mExplorRecyclerView.setLayoutManager(mLayoutManager);
            mExplorRecyclerView.setAdapter(eAdapter);

        }
    }
}
