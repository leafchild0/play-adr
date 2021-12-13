package com.leafchild0.adr.controllers;

import com.google.inject.Inject;
import com.leafchild0.adr.data.AdrRecord;
import com.leafchild0.adr.data.AdrRecordDTO;
import com.leafchild0.adr.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import play.libs.Json;
import play.mvc.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * @author vmalyshev
 */

@Slf4j
public class RecordController extends Controller {

    private final RecordRepository recordRepository;

    @Inject
    public RecordController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public CompletionStage<Result> getAllRecords() {
        return recordRepository.list()
                .thenComposeAsync(records -> CompletableFuture.supplyAsync(()
                        -> ok(Json.toJson(records.stream().map(AdrRecord::toDto).collect(Collectors.toList())))));
    }

    public CompletionStage<Result> getRecordById(String id) {
        return recordRepository.getById(Long.valueOf(id))
                .thenComposeAsync(record -> CompletableFuture.supplyAsync(() -> Results.ok(Json.toJson(record.toDto()))));
    }

    public CompletionStage<Result> updateRecord() {
        AdrRecordDTO dto = Json.fromJson(request().body().asJson(), AdrRecordDTO.class);
        return CompletableFuture.supplyAsync(() -> ok(Json.toJson(recordRepository.update(dto.toRecord()))));
    }

    public CompletionStage<Result> addRecord() {
        AdrRecordDTO dto = Json.fromJson(request().body().asJson(), AdrRecordDTO.class);
        return CompletableFuture.supplyAsync(() -> ok(Json.toJson(
                recordRepository.add(dto.toRecord()))));
    }

}
