package com.atsyg.receipt_details_calculator.products;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Product {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.175");
    private static final BigDecimal ROUNDING_FACTOR = new BigDecimal("0.05");

    private final BigDecimal price;

    Product(String price) {
        this.price = new BigDecimal(price);
    }

    public abstract String getProductName();

    public BigDecimal getPriceExcludingTaxes() {
        return price;
    }

    public BigDecimal getPriceIncludingTaxes() {
        return price.add(getTaxes());
    }

    private BigDecimal calculatePriceIncludingTaxes() {
        BigDecimal priceMultiplier = TAX_RATE.add(BigDecimal.ONE);
        BigDecimal priceIncludingTaxes = price.multiply(priceMultiplier);
        return round(priceIncludingTaxes);
    }

    // rounds up to the upper 0.05
    private BigDecimal round(BigDecimal price) {
        BigDecimal fractionOfPriceOverRoundingFactor = price.divide(ROUNDING_FACTOR, RoundingMode.UP);
        return ROUNDING_FACTOR.multiply(fractionOfPriceOverRoundingFactor.setScale(0, RoundingMode.UP));
    }

    public BigDecimal getTaxes() {
        return calculatePriceIncludingTaxes().subtract(price);
    }

}
