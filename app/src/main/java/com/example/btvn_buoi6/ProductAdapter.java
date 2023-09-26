package com.example.btvn_buoi6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> mListProduct;

    private Context mContext; //mContext chưa được gán giá trị nên bị null => glide k create đc

    public ProductAdapter(ArrayList<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = mListProduct.get(position);

        holder.tvTitle.setText(product.getTitle());
        holder.tvBrand.setText(product.getBrand());
        holder.tvCategory.setText(product.getCategory());
        holder.tvPrice.setText(product.getPrice());
        Glide.with(mContext).load(product.getImage()).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return mListProduct != null? mListProduct.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView tvTitle, tvBrand, tvCategory, tvPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBrand = itemView.findViewById(R.id.tvBrand);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
