package com.example.retrofitapp2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofitapp2.R;
import com.example.retrofitapp2.adapter.HeaderAdapter;
import com.example.retrofitapp2.adapter.HomeLastMealsAdapter;
import com.example.retrofitapp2.model.Meals;
import com.example.retrofitapp2.ui.detail.Detail;

import java.util.List;

public class HomeFragment extends Fragment implements HomeView {
    HomePresenter presenter;
    private HomeView view;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new HomePresenter(this);
        presenter.getRandomMeals();
        presenter.getLastMeals();
    }

    @Override
    public void setRandomMeal(@NonNull List<Meals.Meal> meal) {
        ViewPager viewPager=this.getView().findViewById(R.id.viewPagerHeader);
        HeaderAdapter headerAdapter=new HeaderAdapter(meal, getActivity());
        viewPager.setAdapter(headerAdapter);
        viewPager.setPadding(20,0,100,0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener(new HeaderAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.mealName);
                Intent intent = new Intent(getActivity().getApplicationContext(), Detail.class);
                intent.putExtra("detail", mealName.getText().toString());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void setLastMeal(List<Meals.Meal> meal) {
        RecyclerView recycler=this.getView().findViewById(R.id.recyclerLatestMeals);
        HomeLastMealsAdapter homeLastMealsAdapter=new HomeLastMealsAdapter(meal, getActivity());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setNestedScrollingEnabled(true);
        recycler.setAdapter(homeLastMealsAdapter);
        homeLastMealsAdapter.notifyDataSetChanged();
        homeLastMealsAdapter.setOnItemClickListener(new HomeLastMealsAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.mealName);
                Intent intent = new Intent(getActivity().getApplicationContext(), Detail.class);
                intent.putExtra("detail", mealName.getText().toString());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorLoading(String message) {

    }

}
