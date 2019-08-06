package com.gdunn.owner.recipefeed;

import java.util.List;

public class recipeModel {
    private String title;
    private String author;
    private String authorImage;
    private String recipeImage;
    private String description;
    private List<recipeContent> ingredients_directions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<recipeContent> getIngredients_directions() {
        return ingredients_directions;
    }

    public void setIngredients_directions(List<recipeContent> ingredients_directions) {
        this.ingredients_directions = ingredients_directions;
    }
}
