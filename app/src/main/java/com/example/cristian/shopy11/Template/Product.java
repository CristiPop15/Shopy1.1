package com.example.cristian.shopy11.Template;

/**
 * Created by Cristian on 5/31/2017.
 */

public class Product {

    public int id;
    public String productName;
    public double price;
    public double weight;


    @Override
    public String toString() {
        return productName;
    }
}
