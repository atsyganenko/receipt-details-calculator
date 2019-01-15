package com.atsyg.receipt_details_calculator.products;

import java.math.BigDecimal;

public class MusicCD extends Product {

    private static final BigDecimal FIXED_TAX = new BigDecimal("1.25");

    public MusicCD(String name, String price) {
        super(name, price);
    }

    @Override
    public BigDecimal getPriceIncludingTaxes() {
        return super.getPriceIncludingTaxes().add(FIXED_TAX);
    }

    @Override
    public BigDecimal getTaxes() {
        return super.getTaxes().add(FIXED_TAX);
    }
}
