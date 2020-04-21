package com.axionteq.newsapp.category;

public class Category {

    private String category;

    private int image, background;

    public String getTitle() {
        return getCategory();
    }

    public String getCategory() {
        return category;
    }

    int getBackground() {
        return background;
    }

    int getImage() {
        return image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setImage(int image) {
        this.image = image;
    }

    Category(String category, int background, int image) {
        this.background = background;
        this.category = category;
        this.image = image;
    }

    public Category() {
    }

    public String title(){
        return this.category;
    }

}
