# 1. Build 단계
FROM gradle:8.7.0-jdk21-alpine AS builder

WORKDIR /home/gradle/app

# 의존성 캐싱을 위해 빌드 스크립트만 먼저 복사
COPY build.gradle settings.gradle ./
RUN gradle dependencies --no-daemon || true

# 프로젝트 전체 복사
COPY . .

# 빌드
RUN gradle clean bootJar --no-daemon

# 2. Runtime 단계
FROM eclipse-temurin:21-jre-jammy

# 빌드된 JAR 파일 복사
COPY --from=builder /home/gradle/app/build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]