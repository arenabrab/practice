package com.barbanera.metrics.services;

import com.barbanera.metrics.dtos.MetricDto;
import com.barbanera.metrics.entities.BlockingMetric;
import com.barbanera.metrics.repositories.MetricsPostgresRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlockingService {

    private final MetricsPostgresRepository metricsPostgresRepository;

    public List<BlockingMetric> getMetrics() {
        log.info("Finding all blocking metrics - TEST");
        return metricsPostgresRepository.findAll();
    }

    public BlockingMetric saveMetric(BlockingMetric blockingMetric) {
        return metricsPostgresRepository.saveAndFlush(blockingMetric);
    }

    public void deleteMetric(long id) {
        metricsPostgresRepository.deleteById(id);
    }

    public MetricDto updateMetric(MetricDto metricDto) {
        metricsPostgresRepository.findById(metricDto.id())
                .map(metric ->
                        metric.toBuilder()
                                .type(metricDto.type())
                                .payload(metricDto.payload())
                                .build())
                .ifPresent(metricsPostgresRepository::saveAndFlush);
        return metricDto;
    }
}
