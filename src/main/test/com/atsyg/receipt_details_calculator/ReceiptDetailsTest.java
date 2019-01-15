package com.atsyg.receipt_details_calculator;

import com.atsyg.receipt_details_calculator.products.MedicalProduct;
import com.atsyg.receipt_details_calculator.products.MusicCD;
import com.atsyg.receipt_details_calculator.products.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptDetailsTest {

    @Test
    void singleItemOfEachProduct() {
        List<Product> products = List.of(new Product("book", "29.49"),
                                         new Product("chocolate snack", "0.75"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "1 book: 34.65\n1 chocolate snack: 0.90\nSales Taxes: 5.31\nTotal: 35.55\n";

        assertEquals(expected, actual);
    }

    @Test
    void multipleItemsOfSameProduct() {
        List<Product> products = List.of(new Product("book", "29.49"),
                                         new Product("book", "27.32"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "2 book: 66.75\nSales Taxes: 9.94\nTotal: 66.75\n";

        assertEquals(expected, actual);
    }

    @Test
    void singleMedicalProductItem() {
        List<Product> products = List.of(new MedicalProduct("headache pills", "4.15"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "1 headache pills: 4.15\nSales Taxes: 0\nTotal: 4.15\n";

        assertEquals(expected, actual);
    }

    @Test
    void singleMusicCDItem() {
        List<Product> products = List.of(new MusicCD("music CD", "20.05"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "1 music CD: 24.80\nSales Taxes: 4.75\nTotal: 24.80\n";

        assertEquals(expected, actual);
    }
}