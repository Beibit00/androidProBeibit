package com.example.retrofitapp2.ui.category;

import com.example.retrofitapp2.model.Categories;

import java.util.List;

public interface CategoryView {
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}

