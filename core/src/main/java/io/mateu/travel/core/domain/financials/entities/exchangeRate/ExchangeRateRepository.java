package io.mateu.travel.core.domain.financials.entities.exchangeRate;

import java.time.LocalDateTime;

public interface ExchangeRateRepository {

    ExchangeRate find(String sourceCurrencyIsoCode, String targetCurrencyIsoCode, LocalDateTime workDateTime);

    ExchangeRate save(ExchangeRate exchangeRate);

}
