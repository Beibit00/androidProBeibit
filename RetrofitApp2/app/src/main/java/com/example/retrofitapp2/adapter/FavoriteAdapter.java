package com.example.retrofitapp2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retrofitapp2.R;
import com.example.retrofitapp2.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.RecyclerViewHolder> {

    private List<Meals.Meal> meals;
    private Context context;
    private static ClickListener clickListener;

    public FavoriteAdapter(List<Meals.Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_meal, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String strMealImage=meals.get(position).getStrMealThumb();
        String strMealName=meals.get(position).getStrMeal();
        String strMealCategory=meals.get(position).getStrCategory();
        String strMealArea=meals.get(position).getStrArea();
        Picasso.get().load(strMealImage).placeholder(R.drawable.placeholder).into(holder.mealImage);
        holder.mealTitle.setText(strMealName);
        holder.mealCategory.setText(strMealCategory);
        holder.mealArea.setText(strMealArea);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mealImage;
        TextView mealTitle;
        TextView mealCategory;
        TextView mealArea;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.mealThumb);
            mealTitle=itemView.findViewById(R.id.mealName);
            mealCategory=itemView.findViewById(R.id.mealCategory);
            mealArea=itemView.findViewById(R.id.mealArea);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
    public void setOnItemClickListener(FavoriteAdapter.ClickListener clickListener) {
        FavoriteAdapter.clickListener = clickListener;
    }
    public interface ClickListener{
        void onClick(View view, int position);
    }
}