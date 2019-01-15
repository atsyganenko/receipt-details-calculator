package com.atsyg.receipt_details_calculator.products;

public class DefaultProduct extends Product {

    private final String name;

    public DefaultProduct(String name, String price) {
        super(price);
        this.name = name;
    }

    @Override
    public String getProductName() {
        return name;
    }
}
