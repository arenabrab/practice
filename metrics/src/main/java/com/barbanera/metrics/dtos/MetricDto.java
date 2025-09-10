package com.barbanera.metrics.dtos;

import com.barbanera.metrics.entities.BlockingMetric;
import com.barbanera.metrics.entities.ReactiveMetric;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;

public record MetricDto(
        Long id,
        String type,
        JsonNode payload,
        long version) {

    private static final JsonMapper mapper = JsonMapper.builder().build();

    @SneakyThrows
    public BlockingMetric toBlockingMetric() {
        return BlockingMetric.builder()
                .id(this.id)
                .type(this.type)
                .payload(this.payload)
                .build();
    }

    @SneakyThrows
    public ReactiveMetric toReactiveMetric() {
        return ReactiveMetric.builder()
                .id(this.id)
                .type(this.type)
                .payload(this.payload)
                .build();
    }
}
