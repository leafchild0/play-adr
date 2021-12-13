package com.leafchild0.adr.controllers;

import com.google.inject.Inject;
import com.leafchild0.adr.data.AdrRecord;
import com.leafchild0.adr.data.AdrRecordDTO;
import com.leafchild0.adr.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import play.libs.Json;
import play.mvc.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * @author vmalyshev
 */

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RecordController extends Controller {

    private final RecordRepository recordRepository;

    public CompletionStage<Stream<AdrRecord>> getAllRecords() {
        return recordRepository.list();
    }

    public CompletionStage<Result> getRecordById(Long id) {
        return recordRepository.getById(id).thenComposeAsync(record ->
                record.map(adrRecord -> CompletableFuture.supplyAsync(() -> Results.ok(Json.toJson(adrRecord)))).orElseGet(() -> CompletableFuture.supplyAsync(Results::notFound)));
    }

    public CompletionStage<Result> updateRecord(Long id, AdrRecordDTO dto) {
        return recordRepository.getById(id).thenComposeAsync(record -> record.map(adrRecord -> CompletableFuture.supplyAsync(() -> ok(Json.toJson(
                recordRepository.update(dto.toRecord()))))).orElseGet(() -> CompletableFuture.supplyAsync(Results::notFound)));
    }

    public CompletionStage<Result> addRecord(AdrRecordDTO dto) {
        return CompletableFuture.supplyAsync(() -> ok(Json.toJson(recordRepository.add(dto.toRecord()))));
    }

}
