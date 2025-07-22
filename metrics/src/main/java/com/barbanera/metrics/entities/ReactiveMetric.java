package com.barbanera.metrics.entities;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Builder(toBuilder = true)
@Table(name = "metric")
public record ReactiveMetric(
        @Id
        Long id,
        String type,
        String payload,
        @Version
        long version,
        @CreatedDate
        Long createdDate,
        @LastModifiedDate
        Long lastModifiedDate) {}
