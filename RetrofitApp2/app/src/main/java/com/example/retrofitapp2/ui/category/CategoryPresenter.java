package com.example.retrofitapp2.ui.category;

import com.example.retrofitapp2.Utils;
import com.example.retrofitapp2.model.Categories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {
    CategoryView view;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }
    void getCategories(){
        Call<Categories> categoriesCall= Utils.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if(response.isSuccessful()){
                    view.setCategory(response.body().getCategories());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}