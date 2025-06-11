package com.example.SpringCloudGateway.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component // 스프링이 이 클래스를 bean으로 등록하게 함
@Slf4j

// scgw의 필터를 확장하기 위한 필수 클래스 -> config클래스는 설정값을 받을 수 있도록 정의하는 용도
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    //gateway 필터를 .yml 또는 .java 코드에서 사용할 떄, 커스텀한 설정값을 넣을 수 있도록 만드는 빈 클래스
    public static class Config {
        // 필요시 필드에 넣기
    }

    //abstractGatewayFilterFactory에 Config클래스를 넘겨줘서 Gateway가 이 필터를 사용할 수 있게 초기화함
    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // 필터를 적용할 때 실행되는 메서드, 요청-응답 사이의 중간 단계에서 필터링을 정의함
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest(); // 요청 객체
            ServerHttpResponse response = exchange.getResponse(); // 응답 객체

            // 요청 id나 응답 상태 코드를 로그로 출력
            log.info("Custom PRE filter: request id -> {}", request.getId());
            // ㄴ pre filter : 요청이 들어올 때 로그 출력
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom POST filter: response code -> {}", response.getStatusCode());
            }));
            // ㄴ post filter : 응답이 클라이언트로 나가기 직전에 로그 출력
        };
    }
}