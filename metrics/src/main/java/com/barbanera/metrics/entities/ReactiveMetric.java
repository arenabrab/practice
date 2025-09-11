package com.barbanera.metrics.entities;

import io.r2dbc.postgresql.codec.Json;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Builder(toBuilder = true)
@Table(name = "metric")
public record ReactiveMetric(
        @Id
        Long id,
        String type,
        Json payload,
        @Version
        int version,
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate) {}
