package com.barbanera.metrics;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {





//	@Bean
//	@ServiceConnection
//	KafkaContainer kafkaContainer() {
//		return new KafkaContainer(DockerImageName.parse("apache/kafka-native:latest"));
//	}
//
//	@Bean
//	@ServiceConnection(name = "openzipkin/zipkin")
//	GenericContainer<?> zipkinContainer() {
//		return new GenericContainer<>(DockerImageName.parse("openzipkin/zipkin:latest")).withExposedPorts(9411);
//	}



}
