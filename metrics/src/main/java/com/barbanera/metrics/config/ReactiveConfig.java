package com.barbanera.metrics.config;

import com.barbanera.metrics.repositories.MetricsPostgresReactiveRepository;
import com.barbanera.metrics.repositories.MetricsPostgresRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories(basePackageClasses = {MetricsPostgresReactiveRepository.class},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MetricsPostgresRepository.class )
        })
public class ReactiveConfig {
}
