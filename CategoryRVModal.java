package com.example.newsapp;

public class CategoryRVModal {
    private String Category;
    private String CategoryImageUrl;

    public CategoryRVModal(String category, String categoryImageUrl) {
        this.Category  = category;
        this.CategoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCategoryImageUrl() {
        return CategoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        CategoryImageUrl = categoryImageUrl;
    }
}
