package com.example.retrofitapp2.ui.detail;

import android.support.annotation.NonNull;

import com.example.retrofitapp2.Utils;
import com.example.retrofitapp2.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {
    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    void getMealByName(String mealName){
        Call<Meals> mealCall = Utils.getApi().getMealByName(mealName);

        mealCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                if(response.isSuccessful()) {
                    try {
                        view.setMeal(response.body().getMeals().get(0));
                    }
                    catch (Exception e){
                        view.onErrorLoading();
                    }
                }
                else {
                    view.onErrorLoading();
                }
            }
            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                view.onErrorLoading();
            }
        });
    }

}