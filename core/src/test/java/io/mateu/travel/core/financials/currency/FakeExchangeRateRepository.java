package io.mateu.travel.core.financials.currency;

import io.mateu.travel.core.domain.financials.entities.exchangeRate.ExchangeRate;
import io.mateu.travel.core.domain.financials.entities.exchangeRate.ExchangeRateRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service@Primary
public class FakeExchangeRateRepository implements ExchangeRateRepository {
    @Override
    public ExchangeRate find(String sourceCurrencyIsoCode, String targetCurrencyIsoCode, LocalDateTime workDateTime) {
        return new ExchangeRate(UUID.randomUUID().toString(), sourceCurrencyIsoCode, targetCurrencyIsoCode, 1.25, workDateTime, null);
    }

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {
        return null;
    }
}
