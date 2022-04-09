package io.mateu.travel.core.infrastructure.financials.dal.currency;

import io.mateu.travel.core.domain.financials.entities.currency.Currency;

public class CurrencyJPAMapper {

    public static CurrencyEntity toEntity(Currency model) {
        var entity = new CurrencyEntity();
        entity.setIsoCode(model.getIsoCode());
        entity.setName(model.getName());
        return entity;
    }

    public static Currency toModel(CurrencyEntity entity) {
        var model = new Currency(entity.getIsoCode(), entity.getName());
        return model;
    }

}
