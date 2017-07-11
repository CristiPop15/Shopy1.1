package com.example.cristian.shopy11;

import com.example.cristian.shopy11.Template.Account;
import com.example.cristian.shopy11.Template.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Cristian on 5/31/2017.
 */

public interface ApiService {

    @GET("products.json")
    Call<List<Product>> getProducts();

    @GET("accounts.json")
    Call<List<Account>> getAccounts();
}

