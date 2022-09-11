package com.example.security.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.security.controller")) // 스캔할 패키지 범위
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Open API Test With Swagger")
                .description("설명 부분")
                .version("1.0.0")
                .build();
    }

    /**
     * @ApiOperation 대상 API의 설명을 작성하기 위한 어노테이션
     * @ApiParam 매개변수에 대한 설명 및 설정을 위한 어노테이션. 메서드의 매개변수뿐 아니라
     * DTO 객체를 매개변수로 사용할 경우 DTO 클래스 내의 매개변수에도 정의할 수 있음음
    */
    @ApiOperation(value = "메서드 예제", notes = "Product 예제")
    @GetMapping(value = "/request")
    public String getRequestParam(
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "가격", required = true) @RequestParam Integer price,
            @ApiParam(value = "재고", required = true) @RequestParam Integer stock) {
        return name + ' ' + price + ' ' + stock;
    }
}
