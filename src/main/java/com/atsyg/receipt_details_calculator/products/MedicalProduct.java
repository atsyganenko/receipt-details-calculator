package com.atsyg.receipt_details_calculator.products;

import java.math.BigDecimal;

public class MedicalProduct extends Product {

    public MedicalProduct(String name, String price) {
        super(name, price);
    }

    @Override
    public BigDecimal getPriceIncludingTaxes() {
        return getPriceExcludingTaxes();
    }

    @Override
    public BigDecimal getTaxes() {
        return BigDecimal.ZERO;
    }
}
