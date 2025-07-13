# Infra README

Spring Cloud Netflix Eureka + Gateway + LoadBalancer 구성 가이드

---

## 1. order(product)-server  설정

### 의존성 추가 (build.gradle)

```gradle
implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
````

> ⚠️ Spring Cloud 2022.0 이상에서는 `@EnableEurekaClient`를 사용하지 않는다.

### application 설정

```yaml
spring:
  application:
    name: order-server
server:
  port: 8082
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
```

* `spring.application.name`: Eureka에 등록되는 서비스 이름
* `register-with-eureka`: 나 자신을 Eureka에 등록할지 여부
* `fetch-registry`: 다른 서비스 목록을 가져올지 여부

---

## 2. discovery-server (Eureka Server) 설정

### 의존성 추가

```gradle
implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'
```

### Application 코드

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

### application 설정

```yaml
spring:
  application:
    name: eureka-server
server:
  port: 8761
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
  server:
    enable-self-preservation: false
```

---

## 3. gateway-server 설정

### 의존성 추가

```gradle
implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
implementation 'org.springframework.cloud:spring-cloud-starter-gateway-server-webflux'
```

### application 설정

```yaml
server:
  port: 8080

spring:
  application:
    name: gateway-server
  cloud:
    gateway:
      server:
        webflux:
          discovery:
            locator:
              enabled: true
              lower-case-service-id: true
          routes:
            - id: product-server
              uri: lb://product-server
              predicates:
                - Path=/product-server/**
              filters:
                - StripPrefix=1
            - id: order-server
              uri: lb://order-server
              predicates:
                - Path=/order-server/**
              filters:
                - StripPrefix=1

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

### 라우팅 설정 방식

* **자동 라우팅 (Discovery Locator)**
  Eureka 등록 정보를 기반으로 자동으로 라우트 생성

  ```
  spring.cloud.gateway.server.webflux.discovery.locator.enabled=true
  ```
* **수동 라우팅 (routes 설정)**
  하나하나 경로를 직접 설정

---

## 4. LoadBalancer (lb://)

`lb://`는 Spring Cloud LoadBalancer와 Eureka가 연동되어 서비스 이름 기반으로 인스턴스를 찾고 로드밸런싱한다.

예:

```yaml
uri: lb://order-server
```

* Eureka에 등록된 `order-server` 인스턴스 중 하나로 라우팅
* IP/PORT는 Eureka로부터 동적으로 가져옴

---

## 5. Gateway 호출 흐름

* Gateway가 `/order-server/orders` 호출 수신
* Eureka에 order-server의 인스턴스 정보 조회
* 라우팅 규칙 적용
* order-server로 실제 호출 수행

