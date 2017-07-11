package com.example.cristian.shopy11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cristian.shopy11.Template.CurrentAccount;
import com.example.cristian.shopy11.Template.Product;
import com.example.cristian.shopy11.Tools.LoginActivity;
import com.example.cristian.shopy11.Tools.ShoppingCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button start_button, history_button, login_button, logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("MainActivity", "Cart weight - " + ShoppingCart.getCart().getTotalWeight());

try{        Log.e("MainActivity", CurrentAccount.getInstance().toString());}catch (NullPointerException e){
    Log.e("MainActivity", "null current account");
}

        start_button = (Button)findViewById(R.id.start_button);
        history_button = (Button)findViewById(R.id.history_button);
        login_button = (Button)findViewById(R.id.login_button);
        logout_button = (Button)findViewById(R.id.logout_button);

        Intent i = getIntent();
        if(i.getExtras() != null) {
            if (CurrentAccount.getInstance() != null) {
                login_button.setVisibility(View.GONE);
                logout_button.setVisibility(View.VISIBLE);
            }
        }

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShoppingActivity.class);
                i.putExtra("action", 1);
                Log.e("MainActivity", "start button clicked");
                startActivity(i);
            }
        });

        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HistoryActivity.class);
                Log.e("MainActivity", "history button clicked");
                startActivity(i);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                Log.e("MainActivity", "login button clicked");
                startActivity(i);
            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentAccount.getInstance().logout();
                Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_LONG).show();
                recreate();
            }
        });

    }

}
