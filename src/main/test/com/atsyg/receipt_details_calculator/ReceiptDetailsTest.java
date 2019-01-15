package com.atsyg.receipt_details_calculator;

import com.atsyg.receipt_details_calculator.products.DefaultProduct;
import com.atsyg.receipt_details_calculator.products.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptDetailsTest {

    @Test
    void singleItemOfEachProduct() {
        List<Product> products = List.of(new DefaultProduct("book", "29.49"),
                                         new DefaultProduct("chocolate snack", "0.75"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "1 book: 34.70\n1 chocolate snack: 0.90\nSales Taxes: 5.36\nTotal: 35.60\n";

        assertEquals(expected, actual);
    }
}