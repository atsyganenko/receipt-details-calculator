package com.atsyga.receipt_details_calculator;

import java.math.BigDecimal;

public interface Item {
    String getName();

    BigDecimal getPriceIncludingTaxes();

    BigDecimal getTaxes();
}
