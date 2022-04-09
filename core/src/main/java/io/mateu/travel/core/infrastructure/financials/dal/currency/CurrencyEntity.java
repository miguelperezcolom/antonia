package io.mateu.travel.core.infrastructure.financials.dal.currency;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CurrencyEntity {

    @Id
    private String isoCode;

    private String name;

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
