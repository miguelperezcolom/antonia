package io.mateu.travel.core.domain.financials.services;

import io.mateu.travel.core.domain.financials.entities.exchangeRate.ExchangeRateRepository;
import io.mateu.travel.core.domain.financials.valueObjects.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CurrencyService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public Money convert(Money money, String targetCurrencyIsoCode) {
        var exchangeRate = exchangeRateRepository.find(money.getCurrencyIsoCode(), targetCurrencyIsoCode, LocalDateTime.now());
        return new Money(exchangeRate.getExchangeRate() * money.getAmount(), targetCurrencyIsoCode);
    }

}
