package io.mateu.travel.core.domain.financials.entities.currency;

public interface CurrencyRepository {

    Currency find(String isoCode);

    Currency save(Currency currency);

}
