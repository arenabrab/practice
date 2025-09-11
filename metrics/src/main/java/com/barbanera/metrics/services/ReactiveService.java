package com.barbanera.metrics.services;

import com.barbanera.metrics.dtos.MetricDto;
import com.barbanera.metrics.entities.ReactiveMetric;
import com.barbanera.metrics.repositories.MetricsPostgresReactiveRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.r2dbc.postgresql.codec.Json;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReactiveService {

    private final MetricsPostgresReactiveRepository metricsPostgresReactiveRepository;
    private static final JsonMapper mapper = JsonMapper.builder().build();

    public Flux<ReactiveMetric> getAll() {
        return metricsPostgresReactiveRepository.findAll();
    }

    public Mono<ReactiveMetric> save(ServerRequest request) {
        return request.bodyToMono(MetricDto.class)
                .doOnNext(metricDto -> log.info("Receiving REACTIVE metric {}", metricDto))
                .map(MetricDto::toReactiveMetric)
                .flatMap(metricsPostgresReactiveRepository::save)
                .doOnNext(metric -> log.info("Saving metric: {}", metric.id()));
    }

    public Mono<Void> delete(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return metricsPostgresReactiveRepository.deleteById(id);
    }

    public Mono<ReactiveMetric> update(ServerRequest request) {
        return request.bodyToMono(MetricDto.class)
                        .flatMap(this::update);
    }

    private Mono<ReactiveMetric> update(MetricDto metricDto) {
        return metricsPostgresReactiveRepository
                .findById(metricDto.id())
                .map(dto ->
                        dto.toBuilder()
                                .type(metricDto.type())
                                .payload(toJson(metricDto.payload()))
                                .build())
                .doOnNext(metric -> log.info("Updating metric: {}", metric))
                .flatMap(metricsPostgresReactiveRepository::save);
    }


    @SneakyThrows
    private Json toJson(JsonNode jsonNode) {
        return Json.of(mapper.writeValueAsString(jsonNode.toString()));
    }
}
