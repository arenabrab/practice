package com.barbanera.metrics.controllers;

import com.barbanera.metrics.dtos.MetricDto;
import com.barbanera.metrics.entities.BlockingMetric;
import com.barbanera.metrics.services.BlockingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("Receiving BLOCKING metric {}", metric);
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

    @GetMapping("/foo")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void foo() {
    }

}
