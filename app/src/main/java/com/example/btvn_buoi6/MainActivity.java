package com.example.btvn_buoi6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Product> mListProducts;
    private ProductAdapter mProductAdapter;
    private RecyclerView rvProduct;
    private DBHelper mDBHelper;
    private Button btnTitle, btnCategory, btnBrand;
    private EditText edtPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        rvProduct = findViewById(R.id.rvProduct);
        edtPosition = findViewById(R.id.edtPosition);
        btnTitle = findViewById(R.id.btnFindByTitle);
        btnBrand = findViewById(R.id.btnFindByBrand);
        btnCategory = findViewById(R.id.btnFindByCategory);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(gridLayoutManager);
        rvProduct.setAdapter(mProductAdapter);

        btnTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListProducts = (ArrayList<Product>) mDBHelper.searchByTitle(edtPosition.getText().toString());
                rvProduct = findViewById(R.id.rvProduct);
                mProductAdapter = new ProductAdapter(mListProducts);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                rvProduct.setLayoutManager(gridLayoutManager);
                rvProduct.setAdapter(mProductAdapter);
                mProductAdapter.notifyDataSetChanged();
            }
        });

        btnBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListProducts = (ArrayList<Product>) mDBHelper.searchByBrand(edtPosition.getText().toString());
                rvProduct = findViewById(R.id.rvProduct);
                mProductAdapter = new ProductAdapter(mListProducts);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                rvProduct.setLayoutManager(gridLayoutManager);
                rvProduct.setAdapter(mProductAdapter);
                mProductAdapter.notifyDataSetChanged();
            }
        });

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListProducts = (ArrayList<Product>) mDBHelper.searchByCategory(edtPosition.getText().toString());
                rvProduct = findViewById(R.id.rvProduct);
                mProductAdapter = new ProductAdapter(mListProducts);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                rvProduct.setLayoutManager(gridLayoutManager);
                rvProduct.setAdapter(mProductAdapter);
                mProductAdapter.notifyDataSetChanged();
            }
        });
        mProductAdapter.notifyDataSetChanged();
    }

    private void initData() {
        mListProducts = new ArrayList<>();
        mProductAdapter = new ProductAdapter(mListProducts);
        mDBHelper = new DBHelper(this);
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setTitle("Realme C" + i);
            product.setBrand("REALME");
            product.setCategory("Điện thoại");
            product.setPrice("12.990.00" + i);
            List<String> images = new ArrayList<>();
            images.add("https://cdn.tgdd.vn/Products/Images/42/301603/realme-c35-vang-thumb-600x600.jpg");
            product.setImage(images);
        }
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setTitle("Samsung C" + i);
            product.setBrand("SAMSUNG");
            product.setCategory("Điện thoại");
            product.setPrice("6.990.00" + i);
            List<String> images = new ArrayList<>();
            images.add("https://cdn.tgdd.vn/Products/Images/42/301603/realme-c35-vang-thumb-600x600.jpg");
            product.setImage(images);
            // code này chỉ update data vào sqlite thôi chưa update data cho mListProduct
            // -> không hiện được data trong sql lên rv
            //mDBHelper.addProduct(product);
        }

        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setTitle("Laptop Dell Intel Core i" + i);
            product.setBrand("DELL");
            product.setCategory("Laptop");
            product.setPrice("15.9" + i + "0.000");
            List<String> images = new ArrayList<>();
            images.add("https://cdn.tgdd.vn/Products/Images/42/301603/realme-c35-vang-thumb-600x600.jpg");
            product.setImage(images);
            //mDBHelper.addProduct(product);
            // như ở đây nhé
            // thằng mListProduct chứa data để hiện thị lên rv đúng chưa
            // => muốn rv hiển thị data thì mình cần set data cho cả thằng mlistProduct nữa
            //mDBHelper.getProducts() dùng để get toàn bộ data trong sql ra rồi gán cho mListProduct
            mListProducts.addAll(mDBHelper.getProducts());
        }
    }
}