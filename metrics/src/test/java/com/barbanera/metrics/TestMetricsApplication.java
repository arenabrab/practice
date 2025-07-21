package com.barbanera.metrics;

import org.springframework.boot.SpringApplication;

public class TestMetricsApplication {

	public static void main(String[] args) {
		SpringApplication.from(MetricsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
