package com.leafchild0.adr.repository;

import com.leafchild0.adr.data.AdrRecord;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * @author vmalyshev
 * @date 13.12.2021
 */
public class RecordRepositoryImpl implements RecordRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;
    private AuditReader reader;

    @Inject
    public RecordRepositoryImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Long> add(AdrRecord record) {
        return supplyAsync(() -> wrap(em -> insert(em, record)), executionContext);
    }

    @Override
    public CompletionStage<AdrRecord> update(AdrRecord record) {
        return supplyAsync(() -> wrap(em -> update(em, record)), executionContext);
    }

    @Override
    public CompletionStage<List<AdrRecord>> list() {
        return supplyAsync(() -> wrap(this::list), executionContext);
    }

    @Override
    public CompletionStage<AdrRecord> getById(Long id) {
        return supplyAsync(() -> wrap(em -> findOne(em, id)), executionContext);
    }

    @Override
    public CompletionStage<List<AdrRecord>> getHistoryForRecord(Long id) {
        return supplyAsync(() -> wrap(em -> {
            reader = AuditReaderFactory.get(jpaApi.em());
            return reader.createQuery()
                    .forRevisionsOfEntity(AdrRecord.class, true, true).getResultList();
        }));
    }

    private AdrRecord findOne(EntityManager em, Long id) {
        return em.createQuery("select p from AdrRecord p where id=:id", AdrRecord.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Long insert(EntityManager em, AdrRecord record) {
        em.persist(record);
        return record.id;
    }

    private AdrRecord update(EntityManager em, AdrRecord record) {

        em.merge(record);
        return record;
    }

    private List<AdrRecord> list(EntityManager em) {
        return em.createQuery("select p from AdrRecord p", AdrRecord.class).getResultList();
    }
}
