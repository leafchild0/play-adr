package com.leafchild0.adr.repository;

import com.leafchild0.adr.data.AdrRecord;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * @author vmalyshev
 * @date 13.12.2021
 */
public class RecordRepositoryImpl implements RecordRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

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
    public CompletionStage<Stream<AdrRecord>> list() {
        return supplyAsync(() -> wrap(this::list), executionContext);
    }

    @Override
    public CompletionStage<Optional<AdrRecord>> getById(Long id) {
        return null;
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

    private Stream<AdrRecord> list(EntityManager em) {
        List<AdrRecord> persons = em.createQuery("select p from AdrRecord p", AdrRecord.class).getResultList();
        return persons.stream();
    }
}
