package com.example.retrofitapp2.ui.lastMeal;

import com.example.retrofitapp2.model.Meals;

import java.util.List;

public interface LastMealView {
    void setLastMeal(List<Meals.Meal> meal);
    void onErrorLoading(String message);
}
