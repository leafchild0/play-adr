package com.leafchild0.adr.repository;

import com.google.inject.ImplementedBy;
import com.leafchild0.adr.data.AdrRecord;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * Repository interface for records
 * @author vmalyshev
 * @date 13.12.2021
 */
@ImplementedBy(RecordRepositoryImpl.class)
public interface RecordRepository {

    /**
     * Adds new record
     * @param record - record to be saved
     * @return async id of created record
     */
    CompletionStage<Long> add(AdrRecord record);

    /**
     * Updates existing record by id
     * @param record - record with id
     * @return boolean result of the operation
     */
    CompletionStage<AdrRecord> update(AdrRecord record);

    /**
     * Get all records
     * @return - async stream of records
     */
    CompletionStage<Stream<AdrRecord>> list();

    /**
     * Returns record by id
     * @param id - id to search
     * @return record by id or empty
     */
    CompletionStage<Optional<AdrRecord>> getById(Long id);
}
