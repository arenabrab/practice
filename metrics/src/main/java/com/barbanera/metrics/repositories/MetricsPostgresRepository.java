package com.barbanera.metrics.repositories;

import com.barbanera.metrics.entities.BlockingMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsPostgresRepository extends JpaRepository<BlockingMetric, Long> {
    void deleteById(long id);
}
