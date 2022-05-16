package com.example.retrofitapp2.ui.eachCategory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.retrofitapp2.R;
import com.example.retrofitapp2.adapter.MealByCategoryAdapter;
import com.example.retrofitapp2.model.Meals;
import com.example.retrofitapp2.ui.detail.Detail;

import java.util.List;

public class EachCategoryActivity extends AppCompatActivity implements EachCategoryView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.each_category_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent=getIntent();
        String categoryName=intent.getStringExtra("category");
        getSupportActionBar().setTitle(categoryName);
        EachCategoryPresenter presenter=new EachCategoryPresenter(this);
        presenter.getMealByCategory(categoryName);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void setMeals(List<Meals.Meal> meals) {
        MealByCategoryAdapter adapter=new MealByCategoryAdapter(meals,this);
        RecyclerView recyclerView=findViewById(R.id.eachCategoryRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new MealByCategoryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView mealName = view.findViewById(R.id.mealName);
                Intent intent = new Intent(EachCategoryActivity.this.getApplicationContext(), Detail.class);
                intent.putExtra("detail", mealName.getText().toString());
                EachCategoryActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorLoading(String message) {

    }
}