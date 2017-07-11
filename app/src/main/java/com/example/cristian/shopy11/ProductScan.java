package com.example.cristian.shopy11;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cristian.shopy11.Template.Product;
import com.example.cristian.shopy11.Tools.IntentIntegrator;
import com.example.cristian.shopy11.Tools.IntentResult;
import com.example.cristian.shopy11.Tools.ProductCollection;
import com.example.cristian.shopy11.Tools.ShoppingCart;

import java.util.ArrayList;
import java.util.List;


public class ProductScan extends AppCompatActivity {

    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        products = new ArrayList<>();
        int price = 0;

        for(int i = 0; i<5; i++){
            Product p = new Product();
            p.price = ++price;
            p.productName = String.format("Produsul-%s ", String.valueOf(price));
            products.add(p);
        }


        for(Product p: products)
            ShoppingCart.getCart().addProduct(p);
        Intent i = new Intent(ProductScan.this, ShowItems.class );
        startActivity(i);
        finish();
*/

        IntentIntegrator integrator = new IntentIntegrator(this);

        integrator.initiateScan(IntentIntegrator.ALL_CODE_TYPES);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

                Intent i = new Intent(ProductScan.this, ShowItems.class );
                startActivity(i);
            } else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Product p = getProduct(result.getContents());
                if(p != null) {
                    ShoppingCart.getCart().addProduct(p);
                    Intent i = new Intent(ProductScan.this, ShowItems.class );
                    startActivity(i);
                    finish();

                }

                else
                    error_msg();



            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void error_msg() {
        Toast.makeText(this, "Produsul nu a fost gasit in baza de date", Toast.LENGTH_LONG).show();

    }


    private Product getProduct(String product){
        int id = Integer.parseInt(product);
        for(Product p: ProductCollection.mProductList){
            if(p.id == id)
                return p;
        }

        return null;
    }
}
