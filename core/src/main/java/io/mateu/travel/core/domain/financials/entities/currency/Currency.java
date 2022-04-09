package io.mateu.travel.core.domain.financials.entities.currency;

public class Currency {

    private final String isoCode;

    private String name;


    public Currency(String isoCode, String name) {
        this.isoCode = isoCode;
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
