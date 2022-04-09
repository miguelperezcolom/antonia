package io.mateu.travel.core.financials.currency;

import io.mateu.travel.core.domain.financials.services.CurrencyService;
import io.mateu.travel.core.domain.financials.valueObjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurrencyExchangeTests {

    @Autowired
    CurrencyService currencyService;

    @Test
    void money_when_converted_matches() {
        //given
        var euros = new Money(20, "EUR");

        //when
        var usds = currencyService.convert(euros, "USD");

        //then
        Assertions.assertEquals(euros.getAmount() * 1.25, usds.getAmount());
        Assertions.assertEquals("USD", usds.getCurrencyIsoCode());
    }

}
