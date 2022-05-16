package com.example.retrofitapp2.ui.detail;

import com.example.retrofitapp2.model.Meals;

public interface DetailView {
    void setMeal(Meals.Meal meal);
    void onErrorLoading();
}