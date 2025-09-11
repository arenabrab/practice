package com.barbanera.metrics.entities;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.OffsetDateTime;

@Builder(toBuilder = true)
@Table(name = "metric")
public record ReactiveMetric(
        @Id
        Long id,
        String type,
        JsonNode payload,
        @Version
        int version,
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate) {}
