package com.bootcamptwo.assignment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger2Config {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition") // 그룹 이름
                .pathsToMatch("/api/**")    // localhost:port번호/api/~ 로 시작하는 것들을 다 매칭시킨다
                .build();
    }

    @Bean
    public OpenAPI springExcelOpenAPI() {
        return new OpenAPI()    // Swagger API 명세서에 들어왔을 때 어떻게 보여줄지 Customizing 하는 부분
                .info(new Info().title("assignment API")
                        .description("과제 수행 API 명세서입니다.")
                        .version("v0.0.1"));
    }
}

// 참고 : https://velog.io/@jeong-god/Spring-boot-Swagger-API-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0
