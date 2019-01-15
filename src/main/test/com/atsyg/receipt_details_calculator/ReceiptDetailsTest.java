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
        String expected = "1 book: 34.65\n" +
                          "1 chocolate snack: 0.90\n" +
                          "Sales Taxes: 5.31\n" +
                          "Total: 35.55\n";

        assertEquals(expected, actual);
    }

    @Test
    void multipleItemsOfSameProduct() {
        List<Product> products = List.of(new Product("book", "29.49"),
                                         new Product("book", "29.49"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "2 book: 34.65\n" +
                          "Sales Taxes: 10.32\n" +
                          "Total: 69.30\n";

        assertEquals(expected, actual);
    }

    @Test
    void singleMedicalProductItem() {
        List<Product> products = List.of(new MedicalProduct("headache pills", "4.15"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "1 headache pills: 4.15\n" +
                          "Sales Taxes: 0\n" +
                          "Total: 4.15\n";

        assertEquals(expected, actual);
    }

    @Test
    void singleMusicCDItem() {
        List<Product> products = List.of(new MusicCD("music CD", "20.05"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "1 music CD: 24.80\n" +
                          "Sales Taxes: 4.75\n" +
                          "Total: 24.80\n";

        assertEquals(expected, actual);
    }

    @Test
    void taskSubmission_scenarioOne() {
        List<Product> products = List.of(new Product("book", "29.49"),
                                         new MusicCD("music CD", "15.99"),
                                         new Product("chocolate snack", "0.75"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "1 book: 34.65\n" +
                          "1 music CD: 20.05\n" +
                          "1 chocolate snack: 0.90\n" +
                          "Sales Taxes: 9.37\n" +
                          "Total: 55.60\n";

        assertEquals(expected, actual);
    }

    @Test
    void taskSubmission_scenarioTwo() {
        List<Product> products = List.of(new Product("bottle of wine", "20.99"),
                                         new MedicalProduct("box of headache pills", "4.15"),
                                         new Product("box of pins", "11.25"),
                                         new MusicCD("music CD", "14.99"));

        String actual = new ReceiptDetails(products).calculate();
        String expected = "1 bottle of wine: 24.65\n" +
                          "1 box of headache pills: 4.15\n" +
                          "1 box of pins: 13.20\n" +
                          "1 music CD: 18.85\n" +
                          "Sales Taxes: 9.47\n"+
                          "Total: 60.85\n";

        assertEquals(expected, actual);

    }
}