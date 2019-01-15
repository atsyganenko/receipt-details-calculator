package com.atsyg.receipt_details_calculator.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    private static final String PRODUCT_NAME = "test product";

    @DisplayName("Calculate final price (including taxes)")
    @ParameterizedTest(name = "Final price for the product with price={0} should be {1}")
    @CsvSource({"10, 11.75", "29.49, 34.65", "2.37, 2.80", "1, 1.20", "0.00, 0.00"})
    void getPriceIncludingTaxes_returnsPriceWithTaxesApplied(String basePrice, String finalPrice) {
        Product product = new Product(PRODUCT_NAME, basePrice);

        BigDecimal expected = new BigDecimal(finalPrice);
        assertEquals(expected, product.getPriceIncludingTaxes());
    }

    @DisplayName("Calculate taxes")
    @ParameterizedTest(name = "Taxes applied to the product with price={0} should be {1}")
    @CsvSource({"10, 1.75", "29.49, 5.16", "2.37, 0.43", "1, 0.20", "0.00, 0.00"})
    void getTaxes_returnsAmountOfTaxesApplied(String basePrice, String taxes) {
        Product product = new Product(PRODUCT_NAME, basePrice);

        BigDecimal expected = new BigDecimal(taxes);
        assertEquals(expected, product.getTaxes());
    }

    @Test
    void getPriceExcludingTaxes_returnsBasePrice() {
        Product product = new Product(PRODUCT_NAME, "10");

        BigDecimal expected = new BigDecimal("10");
        assertEquals(expected, product.getPriceExcludingTaxes());
    }

}