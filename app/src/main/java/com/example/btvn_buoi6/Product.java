package com.example.btvn_buoi6;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("brand")
    @Expose
    private String brand;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("image")
    @Expose
    private List<String> images;

    public Product() {

    }

    public Product(String title, String brand, String category, String price, List<String> image) {
        this.title = title;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.images = image;
    }

    public List<String> getImage() {
        return images;
    }

    public void setImage(List<String> image) {
        this.images = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
