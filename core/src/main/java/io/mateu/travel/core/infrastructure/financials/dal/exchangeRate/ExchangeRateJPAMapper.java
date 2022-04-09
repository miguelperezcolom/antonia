package io.mateu.travel.core.infrastructure.financials.dal.exchangeRate;

import io.mateu.travel.core.domain.financials.entities.exchangeRate.ExchangeRate;

public class ExchangeRateJPAMapper {

    public static ExchangeRateEntity toEntity(ExchangeRate model) {
        var entity = new ExchangeRateEntity();
        entity.setId(model.getId());
        entity.setFromCurrencyIsoCode(model.getFromCurrencyIsoCode());
        entity.setToCurrencyIsoCode(model.getToCurrencyIsoCode());
        entity.setExchangeRate(model.getExchangeRate());
        entity.setStart(model.getStart());
        entity.setEnd(model.getEnd());
        return entity;
    }

    public static ExchangeRate toModel(ExchangeRateEntity entity) {
        var model = new ExchangeRate(entity.getId(), entity.getFromCurrencyIsoCode(), entity.getToCurrencyIsoCode()
                , entity.getExchangeRate(), entity.getStart(), entity.getEnd());
        return model;
    }

}
