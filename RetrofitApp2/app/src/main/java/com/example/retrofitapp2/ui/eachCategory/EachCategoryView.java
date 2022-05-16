package com.example.retrofitapp2.ui.eachCategory;

import com.example.retrofitapp2.model.Meals;

import java.util.List;

public interface EachCategoryView {
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}