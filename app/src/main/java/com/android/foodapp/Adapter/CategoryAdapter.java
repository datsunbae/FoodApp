package com.android.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.foodapp.Models.Category;
import com.android.foodapp.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> categoryList;

    public CategoryAdapter(List<Category> categoryList){
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category temp = categoryList.get(position);
        holder.txtCategory.setText(temp.title);
        Context context = holder.imgCategory.getContext();
        int imageId = context.getResources().getIdentifier(temp.image, "drawable", context.getPackageName());
        if(imageId !=0){
            holder.imgCategory.setImageResource(imageId);
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCategory;
        ImageView imgCategory;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            imgCategory = itemView.findViewById(R.id.imgCategory);
        }
    }
}
