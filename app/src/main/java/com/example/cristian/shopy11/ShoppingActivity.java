package com.example.cristian.shopy11;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.example.cristian.shopy11.Template.Product;
import com.example.cristian.shopy11.Tools.ErrorScreen;
import com.example.cristian.shopy11.Tools.ProductCollection;
import com.example.cristian.shopy11.Tools.ShoppingCart;
import com.example.cristian.shopy11.Tools.SuccessScreen;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener{

    private ApiService mService;

    private QRCodeReaderView mQRCodeReaderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        Log.e("MainActivity", "Shopping Activity");

        checkPermissions();
    }


    private void checkPermissions() {
        boolean permissionAccepted = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
        if (permissionAccepted) {
            loadCamera();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        checkPermissions();
    }

    private void loadCamera() {
        mQRCodeReaderView = (QRCodeReaderView) findViewById(R.id.reader);
        mQRCodeReaderView.setOnQRCodeReadListener(this);
        mQRCodeReaderView.setAutofocusInterval(2000L);
        mQRCodeReaderView.setTorchEnabled(true);
        mQRCodeReaderView.setQRDecodingEnabled(true);
        mQRCodeReaderView.setBackCamera();
        mQRCodeReaderView.startCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mQRCodeReaderView != null) {
            mQRCodeReaderView.startCamera();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mQRCodeReaderView != null) {
            mQRCodeReaderView.stopCamera();
        }
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        onPause();

        Intent i = this.getIntent();
        int action = i.getExtras().getInt("action");

        if(action == 1) {
            setupRetrofit();
            getProducts();

            try {
                ShoppingCart.getCart().setCartWeight(Double.parseDouble(text));
                Intent goToItems = new Intent(this, ShowItems.class);
                Log.e("MainActivity", "Cart weight = "+text);
                startActivity(goToItems);
                finish();
            } catch (Exception e) {
                Toast.makeText(ShoppingActivity.this, "Cart weight error --text instead of number", Toast.LENGTH_LONG).show();
                onResume();
            }
        }
        else if(action == 0){
            double weight = Double.parseDouble(text);
            Log.e("MainActivity", "Cart total weight = "+ShoppingCart.getCart().getTotalWeight()+" ------  now weight:"+weight);



            if((weight > (ShoppingCart.getCart().getTotalWeight() + 0.50)) || (weight < (ShoppingCart.getCart().getTotalWeight() - 0.50))){
                Log.e("MainActivity", "Error");

                Intent error = new Intent(this, ErrorScreen.class);
                startActivity(error);

            }
            else{
                Intent success = new Intent(this, SuccessScreen.class);
                startActivity(success);

            }
        }

    }


    private void setupRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/CristiPop15/Shopy/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(ApiService.class);
    }


    private void getProducts() {
        Call<List<Product>> call = mService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                ProductCollection.mProductList = response.body();
                Toast.makeText(ShoppingActivity.this, "Products were loaded from server!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ShoppingActivity.this, "Error getting the products. Please restart app. Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("MainActivity", t.getMessage());
            }
        });
    }
}
