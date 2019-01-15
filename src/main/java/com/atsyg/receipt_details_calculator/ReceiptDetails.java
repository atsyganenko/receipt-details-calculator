package com.atsyg.receipt_details_calculator;

import com.atsyg.receipt_details_calculator.products.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class ReceiptDetails {

    private final List<Product> products;

    public ReceiptDetails(List<Product> products) {
        this.products = Collections.unmodifiableList(products);
    }

    public String calculate() {
        StringBuilder builder = new StringBuilder();
        appendDetailsForEachProduct(builder);
        appendTotalTaxes(builder);
        appendTotalPrice(builder);
        return builder.toString();
    }

    private void appendDetailsForEachProduct(StringBuilder builder) {
        products.stream().collect(groupingBy(Product::getProductName, LinkedHashMap::new, toList()))
                .forEach((productName, items) -> {builder.append(calculateProductDetails(productName, items));});
    }

    private void appendTotalTaxes(StringBuilder builder) {
        builder.append(String.format("Sales Taxes: %s\n", calculateTotalTaxes()));
    }

    private void appendTotalPrice(StringBuilder builder) {
        builder.append(String.format("Total: %s\n", calculateTotalPrice()));
    }

    private BigDecimal calculateTotalTaxes() {
        return products.stream().map(Product::getTaxes).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalPrice() {
        return products.stream().map(Product::getPriceIncludingTaxes).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String calculateProductDetails(String productName, List<Product> items) {
        BigDecimal priceIncludingTaxes = items.stream().map(Product::getPriceIncludingTaxes)
                                              .reduce(BigDecimal.ZERO, BigDecimal::add);
        return String.format("%d %s: %s\n", items.size(), productName, priceIncludingTaxes);
    }
}
