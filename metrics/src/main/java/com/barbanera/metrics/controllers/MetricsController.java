package com.barbanera.metrics.controllers;

import com.barbanera.metrics.dtos.MetricDto;
import com.barbanera.metrics.entities.BlockingMetric;
import com.barbanera.metrics.services.BlockingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("metrics/blocking")
@RequiredArgsConstructor
public class MetricsController {

    private final BlockingService blockingService;

    @GetMapping
    public List<BlockingMetric> getMetrics() {
        return blockingService.getMetrics();
    }

    @PostMapping
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public BlockingMetric postMetric(@RequestBody MetricDto metric) {
        return blockingService.saveMetric(metric.toBlockingMetric());
    }

    @PutMapping
    public MetricDto updateMetric(@RequestBody MetricDto metric) {
        return blockingService.updateMetric(metric);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteMetric(@PathVariable("id") long id) {
        blockingService.deleteMetric(id);
    }
}
