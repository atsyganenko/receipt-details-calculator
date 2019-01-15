package com.atsyg.receipt_details_calculator.products;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    private static final String PRODUCT_NAME = "test product";

    @Test
    void getPriceIncludingTaxes_returnsPriceWithTaxesApplied() {
        Product product = new Product(PRODUCT_NAME, "10");

        BigDecimal expected = new BigDecimal(11.75);
        assertEquals(expected, product.getPriceIncludingTaxes());
    }

    @Test
    // rounds up to the nearest 0.05
    void getPriceIncludingTaxes_roundsUpPriceAccordingToPolicy() {
        Product product = new Product(PRODUCT_NAME, "29.49");

        BigDecimal expected = new BigDecimal("34.65");
        assertEquals(expected, product.getPriceIncludingTaxes());
    }

    @Test
    void getTaxes_returnsAmountOfTaxesApplied() {
        Product product = new Product(PRODUCT_NAME, "10");

        BigDecimal expected = new BigDecimal(1.75);
        assertEquals(expected, product.getTaxes());
    }

    @Test
    void getPriceExcludingTaxes_returnsBasePrice() {
        Product product = new Product(PRODUCT_NAME, "10");

        BigDecimal expected = new BigDecimal("10");
        assertEquals(expected, product.getPriceExcludingTaxes());
    }

}