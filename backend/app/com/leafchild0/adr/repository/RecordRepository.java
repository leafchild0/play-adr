package com.leafchild0.adr.repository;

import com.google.inject.ImplementedBy;
import com.leafchild0.adr.data.AdrRecord;

import java.util.List;
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
    CompletionStage<List<AdrRecord>> list();

    /**
     * Returns record by id
     * @param id - id to search
     * @return record by id or empty
     */
    CompletionStage<AdrRecord> getById(Long id);

    /**
     * Returns list of history by id
     * @param id - id to search
     * @return record by id or empty
     */
    CompletionStage<List<AdrRecord>> getHistoryForRecord(Long id);
}
