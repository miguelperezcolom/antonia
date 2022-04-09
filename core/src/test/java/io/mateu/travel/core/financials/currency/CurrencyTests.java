package io.mateu.travel.core.financials.currency;

import io.mateu.travel.core.domain.financials.entities.currency.Currency;
import io.mateu.travel.core.domain.financials.entities.currency.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurrencyTests {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void new_currency_save_saved() {
        //given
        Currency eur = new Currency("EUR", "Euro");

        //when
        currencyRepository.save(eur);

        //then
        var found = currencyRepository.find("EUR");
        Assertions.assertNotNull(found);
        Assertions.assertEquals("EUR", found.getIsoCode());
        Assertions.assertEquals("Euro", found.getName());
    }

    @Test
    public void existing_currency_save_saved() {
        //given
        Currency eur = new Currency("EUR", "Euro");
        currencyRepository.save(eur);
        var existing = currencyRepository.find("EUR");

        //when
        existing.setName("Euro modified");
        currencyRepository.save(existing);

        //then
        var found = currencyRepository.find("EUR");
        Assertions.assertNotNull(found);
        Assertions.assertEquals("EUR", found.getIsoCode());
        Assertions.assertEquals("Euro modified", found.getName());
    }

}
