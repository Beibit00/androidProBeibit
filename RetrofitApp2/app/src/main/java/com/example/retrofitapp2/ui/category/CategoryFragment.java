package com.example.retrofitapp2.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofitapp2.R;
import com.example.retrofitapp2.adapter.CategoryAdapter;
import com.example.retrofitapp2.model.Categories;
import com.example.retrofitapp2.ui.eachCategory.EachCategoryActivity;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryView{


    CategoryPresenter presenter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new CategoryPresenter(this);
        presenter.getCategories();
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerView recyclerView=this.getView().findViewById(R.id.recyclerCategory);
        CategoryAdapter categoryAdapter=new CategoryAdapter(category, getActivity());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView categoryName = view.findViewById(R.id.categoryName);
                Intent intent = new Intent(getActivity().getApplicationContext(), EachCategoryActivity.class);
                intent.putExtra("category", categoryName.getText().toString());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorLoading(String message) {

    }
}