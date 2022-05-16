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
import com.example.retrofitapp2.model.Categories;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder> {
    List<Categories.Category> categories;
    Context context;
    private static ClickListener clickListener;

    public CategoryAdapter(List<Categories.Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String strCategoryImage = categories.get(position).getStrCategoryThumb();
        String strCategoryName = categories.get(position).getStrCategory();
        Picasso.get().load(strCategoryImage).placeholder(R.drawable.placeholder).into(holder.categoryImage);
        holder.categoryTitle.setText(strCategoryName);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView categoryImage;
        TextView categoryTitle;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage=itemView.findViewById(R.id.categoryThumb);
            categoryTitle=itemView.findViewById(R.id.categoryName);
            itemView.setOnClickListener(this);
        }
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }
    public void setOnItemClickListener(CategoryAdapter.ClickListener clickListener) {
        CategoryAdapter.clickListener = clickListener;
    }
    public interface ClickListener {
        void onClick(View view, int position);
    }
}