package com.barbanera.metrics.dtos;

import com.barbanera.metrics.entities.BlockingMetric;
import com.barbanera.metrics.entities.ReactiveMetric;

public record MetricDto(
        Long id,
        String type,
        String payload,
        long version) {

    public BlockingMetric toBlockingMetric() {
        return BlockingMetric.builder()
                .id(this.id)
                .type(this.type)
                .payload(this.payload)
                .build();
    }

    public ReactiveMetric toReactiveMetric() {
        return ReactiveMetric.builder()
                .id(this.id)
                .type(this.type)
                .payload(this.payload)
                .build();
    }
}
