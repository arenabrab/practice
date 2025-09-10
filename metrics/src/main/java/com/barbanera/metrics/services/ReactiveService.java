package com.barbanera.metrics.services;

import com.barbanera.metrics.dtos.MetricDto;
import com.barbanera.metrics.entities.ReactiveMetric;
import com.barbanera.metrics.repositories.MetricsPostgresReactiveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReactiveService {

    private final MetricsPostgresReactiveRepository metricsPostgresReactiveRepository;

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
                                .payload(metricDto.payload())
                                .build())
                .doOnNext(metric -> log.info("Updating metric: {}", metric))
                .flatMap(metricsPostgresReactiveRepository::save);
    }
}
