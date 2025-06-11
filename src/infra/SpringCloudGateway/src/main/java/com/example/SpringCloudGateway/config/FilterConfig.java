package com.example.SpringCloudGateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class FilterConfig {
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes() //라우팅을 정의하는 빌더 시작
//                //first-service
//                .route(r -> r.path("/first-service/**") //사용자의 요청이 url이 이렇다면
//                        .uri("http://localhost:8081")) // 그 요청을 8081포트로 프록시 해줌
//                //second-service
//                .route(r -> r.path("/second-service/**")
//                        .filters(f -> f.addResponseHeader("second-request", "second-request-header") // 헤더를 추가하는 필터를 등록함
//                                .addResponseHeader("second-response", "second-response-header"))
//                        .uri("http://localhost:8082"))
//                .build();
//    }
//}

@Configuration
public class FilterConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                //product
                .route(r -> r.path("/product/**")
                        .filters(f -> f.addResponseHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8081"))
                //order
                .route(r -> r.path("/order/**")
                        .filters(f -> f.addResponseHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8082"))
                .build();
    }
}