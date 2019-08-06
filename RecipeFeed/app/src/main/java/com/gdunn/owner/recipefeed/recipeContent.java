package com.gdunn.owner.recipefeed;

public class recipeContent {
    public static final int TITLE_TYPE=0;
    public static final int INGREDIENTS_TYPE=1;
    public static final int DIRECTIONS_TYPE=2;

    private int type;
    private String collectedContent;

    public recipeContent(int type, String collectedContent){
        this.type = type;
        this.collectedContent = collectedContent;
    }
    public recipeContent(){}
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCollectedContent() {
        return collectedContent;
    }

    public void setCollectedContent(String collectedContent) {
        this.collectedContent = collectedContent;
    }

}
