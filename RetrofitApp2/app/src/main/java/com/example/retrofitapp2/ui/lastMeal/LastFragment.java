package com.example.retrofitapp2.ui.lastMeal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitapp2.R;
import com.example.retrofitapp2.adapter.HomeLastMealsAdapter;
import com.example.retrofitapp2.model.Meals;

import java.util.List;

public class LastFragment extends Fragment implements LastMealView{
    LastMealPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_last, container, false);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new LastMealPresenter(this);
        presenter.getLastMeals();
    }

    @Override
    public void setLastMeal(List<Meals.Meal> meal) {
        RecyclerView recycler=this.getView().findViewById(R.id.recyclerLatestMeals);
        HomeLastMealsAdapter homeLastMealsAdapter=new HomeLastMealsAdapter(meal, getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 5,GridLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setNestedScrollingEnabled(true);
        recycler.setAdapter(homeLastMealsAdapter);
        homeLastMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorLoading(String message) {

    }
}