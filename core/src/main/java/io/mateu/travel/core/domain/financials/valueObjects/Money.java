package io.mateu.travel.core.domain.financials.valueObjects;

public class Money {

    private final double amount;

    private final String currencyIsoCode;

    public Money(double amount, String currencyIsoCode) {
        this.amount = amount;
        this.currencyIsoCode = currencyIsoCode;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrencyIsoCode() {
        return currencyIsoCode;
    }
}
