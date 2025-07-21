package com.barbanera.metrics.repositories;

import com.barbanera.metrics.entities.ReactiveMetric;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MetricsPostgresReactiveRepository extends ReactiveCrudRepository<ReactiveMetric, Long> {
}
