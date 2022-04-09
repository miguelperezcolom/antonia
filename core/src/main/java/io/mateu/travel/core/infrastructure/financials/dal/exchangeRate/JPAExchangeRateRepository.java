package io.mateu.travel.core.infrastructure.financials.dal.exchangeRate;

import io.mateu.travel.core.domain.financials.entities.exchangeRate.ExchangeRate;
import io.mateu.travel.core.domain.financials.entities.exchangeRate.ExchangeRateRepository;
import io.mateu.travel.core.infrastructure.financials.dal.currency.CurrencyEntity;
import io.mateu.travel.core.infrastructure.financials.dal.currency.CurrencyJPAMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JPAExchangeRateRepository implements ExchangeRateRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ExchangeRate find(String sourceCurrencyIsoCode, String targetCurrencyIsoCode, LocalDateTime workDateTime) {
        List<ExchangeRateEntity> found = em.createQuery("select r from " + ExchangeRateEntity.class.getName() + " r where r.fromIsoCode = :from and r.toIsoCode = :to and r.start <= :date order by r.start desc limit 1")
                .setParameter("from", sourceCurrencyIsoCode)
                .setParameter("to", targetCurrencyIsoCode)
                .setParameter("date", workDateTime)
                .getResultList();
        return ExchangeRateJPAMapper.toModel(found.get(0));
    }

    @Override@Transactional
    public ExchangeRate save(ExchangeRate exchangeRate) {
        var entity = ExchangeRateJPAMapper.toEntity(exchangeRate);
        em.merge(entity);
        return ExchangeRateJPAMapper.toModel(entity);
    }
}
