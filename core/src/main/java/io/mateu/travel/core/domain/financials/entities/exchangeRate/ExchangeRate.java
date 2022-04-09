package io.mateu.travel.core.domain.financials.entities.exchangeRate;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExchangeRate {

    private final String id;

    private final String fromCurrencyIsoCode;

    private final String toCurrencyIsoCode;

    private final double exchangeRate;

    private final LocalDateTime start;

    private LocalDateTime end;


    public ExchangeRate(String id, String fromCurrencyIsoCode, String toCurrencyIsoCode, double exchangeRate,
                        LocalDateTime start, LocalDateTime end) {
        this.fromCurrencyIsoCode = fromCurrencyIsoCode;
        this.toCurrencyIsoCode = toCurrencyIsoCode;
        this.exchangeRate = exchangeRate;
        this.start = start;
        this.end = end;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFromCurrencyIsoCode() {
        return fromCurrencyIsoCode;
    }

    public String getToCurrencyIsoCode() {
        return toCurrencyIsoCode;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
