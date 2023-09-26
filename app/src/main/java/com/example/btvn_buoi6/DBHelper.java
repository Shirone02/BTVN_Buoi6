package com.example.btvn_buoi6;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="product.db";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "product";
    public static final String PRODUCT_TITLE = "title";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_BRAND = "brand";
    public static final String PRODUCT_CATEGORY = "category";
    public static final String PRODUCT_IMAGES = "images";


    public DBHelper(@Nullable Context context){
        super(context,DATABASE_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                + PRODUCT_TITLE + " TEXT NOT NULL PRIMARY KEY,"
                + PRODUCT_BRAND + " TEXT NOT NULL,"
                + PRODUCT_CATEGORY + " TEXT NOT NULL,"
                + PRODUCT_PRICE + " TEXT NOT NULL,"
                + PRODUCT_IMAGES + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addProduct(Product product){
        if(product != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(PRODUCT_TITLE, product.getTitle());
            contentValues.put(PRODUCT_BRAND, product.getBrand());
            contentValues.put(PRODUCT_CATEGORY, product.getCategory());
            contentValues.put(PRODUCT_PRICE, product.getPrice());

            Gson gson = new Gson();
            Type typeToken = new TypeToken<List<String>>(){}.getType();
            String data = gson.toJson(product.getImage(), typeToken);
            contentValues.put(PRODUCT_IMAGES, data);

            db.insert(TABLE_NAME, null, contentValues);
            db.close();
        }
    }
    @SuppressLint("Range")
    public List<Product> getProducts(){
        List<Product> listProducts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        if(cursor != null) {
            while(cursor.moveToNext()){
                Product product = new Product();
                product.setTitle(cursor.getString(cursor.getColumnIndex(PRODUCT_TITLE)));
                product.setBrand(cursor.getString(cursor.getColumnIndex(PRODUCT_BRAND)));
                product.setCategory(cursor.getString(cursor.getColumnIndex(PRODUCT_CATEGORY)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE)));

                Gson gson = new Gson();
                Type typeToken = new TypeToken<List<String>>(){}.getType();
                List<String> data = gson.fromJson(cursor.getString(cursor.getColumnIndex(PRODUCT_IMAGES)), typeToken);

                product.setImage(data);
                listProducts.add(product);
            }
        }
        return listProducts;
    }
    @SuppressLint("Range")
    public List<Product> searchByTitle(String title){
        List<Product> listProductsResult = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + PRODUCT_TITLE + " LIKE '%" + title + "%'";
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setTitle(cursor.getString(cursor.getColumnIndex(PRODUCT_TITLE)));
                product.setBrand(cursor.getString(cursor.getColumnIndex(PRODUCT_BRAND)));
                product.setCategory(cursor.getString(cursor.getColumnIndex(PRODUCT_CATEGORY)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE)));

                Gson gson = new Gson();
                Type typeToken = new TypeToken<List<String>>(){}.getType();
                List<String> data = gson.fromJson(cursor.getString(cursor.getColumnIndex(PRODUCT_IMAGES)), typeToken);

                product.setImage(data);
                listProductsResult.add(product);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  listProductsResult;
    }

    @SuppressLint("Range")
    public List<Product> searchByBrand(String brand){
        List<Product> listProductsResult = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + PRODUCT_BRAND + " LIKE '%" + brand + "%'";
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setTitle(cursor.getString(cursor.getColumnIndex(PRODUCT_TITLE)));
                product.setBrand(cursor.getString(cursor.getColumnIndex(PRODUCT_BRAND)));
                product.setCategory(cursor.getString(cursor.getColumnIndex(PRODUCT_CATEGORY)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE)));

                Gson gson = new Gson();
                Type typeToken = new TypeToken<List<String>>(){}.getType();
                List<String> data = gson.fromJson(cursor.getString(cursor.getColumnIndex(PRODUCT_IMAGES)), typeToken);

                product.setImage(data);
                listProductsResult.add(product);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  listProductsResult;
    }

    @SuppressLint("Range")
    public List<Product> searchByCategory(String category){
        List<Product> listProductsResult = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + PRODUCT_CATEGORY + " LIKE '%" + category + "%'";
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setTitle(cursor.getString(cursor.getColumnIndex(PRODUCT_TITLE)));
                product.setBrand(cursor.getString(cursor.getColumnIndex(PRODUCT_BRAND)));
                product.setCategory(cursor.getString(cursor.getColumnIndex(PRODUCT_CATEGORY)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE)));

                Gson gson = new Gson();
                Type typeToken = new TypeToken<List<String>>(){}.getType();
                List<String> data = gson.fromJson(cursor.getString(cursor.getColumnIndex(PRODUCT_IMAGES)), typeToken);

                product.setImage(data);
                listProductsResult.add(product);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  listProductsResult;
    }
}
