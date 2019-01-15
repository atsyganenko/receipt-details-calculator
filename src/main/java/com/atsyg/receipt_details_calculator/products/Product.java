package com.atsyg.receipt_details_calculator.products;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.175");
    private static final BigDecimal ROUNDING_FACTOR = new BigDecimal("0.05");

    private final BigDecimal price;
    private final String name;

    public Product(String name, String price) {
        this.price = new BigDecimal(price);
        this.name = name;
    }

    public String getProductName() {
        return this.name;
    }

    public BigDecimal getPriceExcludingTaxes() {
        return price;
    }

    public BigDecimal getPriceIncludingTaxes() {
        return calculatePriceIncludingTaxes();
    }

    private BigDecimal calculatePriceIncludingTaxes() {
        BigDecimal priceMultiplier = TAX_RATE.add(BigDecimal.ONE);
        BigDecimal priceIncludingTaxes = price.multiply(priceMultiplier);
        return round(priceIncludingTaxes);
    }

    private BigDecimal round(BigDecimal price) {
        BigDecimal fractionOfPriceOverRoundingFactor = price.divide(ROUNDING_FACTOR, RoundingMode.HALF_UP);
        return ROUNDING_FACTOR.multiply(fractionOfPriceOverRoundingFactor.setScale(0, RoundingMode.HALF_UP));
    }

    public BigDecimal getTaxes() {
        return calculatePriceIncludingTaxes().subtract(price);
    }

}
