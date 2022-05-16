package com.example.retrofitapp2.ui.home;

import com.example.retrofitapp2.model.Meals;

import java.util.List;

public interface HomeView {

    void setRandomMeal(List<Meals.Meal> meal);
    void setLastMeal(List<Meals.Meal> meal);
    void onErrorLoading(String message);
}