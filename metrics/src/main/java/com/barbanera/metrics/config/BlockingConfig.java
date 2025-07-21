package com.barbanera.metrics.config;

import com.barbanera.metrics.repositories.MetricsPostgresReactiveRepository;
import com.barbanera.metrics.repositories.MetricsPostgresRepository;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(DataSourceAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses = MetricsPostgresRepository.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MetricsPostgresReactiveRepository.class )
        })
@EnableJpaAuditing
public class BlockingConfig {
}
