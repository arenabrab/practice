package com.barbanera.metrics.config;

import com.barbanera.metrics.entities.ReactiveMetric;
import com.barbanera.metrics.services.ReactiveService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

@Configuration
public class RoutesConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ReactiveService reactiveService) {
        return RouterFunctions
                .nest(
                        RequestPredicates.path("metrics"),
                        RouterFunctions.route().path("reactive", builder ->
                        builder
                                .GET(req -> ServerResponse.ok().body(reactiveService.getAll(), ReactiveMetric.class))
                                .POST( req -> ServerResponse.created(URI.create("metrics/reactive")).body(reactiveService.save(req), ReactiveMetric.class))
                                .PUT( req -> ServerResponse.ok().body(reactiveService.update(req), ReactiveMetric.class))
                                .DELETE( req -> ServerResponse.ok().body(reactiveService.delete(req), Void.class)))
                                .build())
                .andRoute(RequestPredicates.GET("/foobar"), request -> ServerResponse.noContent().build());
    }
}
