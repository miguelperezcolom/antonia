package io.mateu.travel.core.infrastructure.financials.dal.currency;

import io.mateu.travel.core.domain.financials.entities.currency.Currency;
import io.mateu.travel.core.domain.financials.entities.currency.CurrencyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JPACurrencyRepository implements CurrencyRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Currency find(String isoCode) {
        return CurrencyJPAMapper.toModel(em.find(CurrencyEntity.class, isoCode));
    }

    @Override@Transactional
    public Currency save(Currency currency) {
        var entity = CurrencyJPAMapper.toEntity(currency);
        em.merge(entity);
        return CurrencyJPAMapper.toModel(entity);
    }
}
