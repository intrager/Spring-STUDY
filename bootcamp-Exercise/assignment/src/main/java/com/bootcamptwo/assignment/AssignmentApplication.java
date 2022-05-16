package com.bootcamptwo.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
// @EnableSwagger2 // 후에 오류 해결 과정에 기록하기 위하여 일부러 남겼습니다.
public class AssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

    //CORS 필터
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CorsFilter(source));
        registrationBean.setOrder(0);
        return registrationBean;
    }

//  얘도 기록하기 위해 남겼습니다.
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.bootcamptwo.assignment"))
//                .paths(PathSelectors.any())
//                .build();
//    }
    // Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException
    // --> implementation 'io.springfox:springfox-swagger2:3.0.0' implementation 'io.springfox:springfox-swagger-ui:3.0.0'
    // 위 두 라이브러리는 2020년 이후로 업데이트가 멈췄기 때문에 spring boot 2.6.x 버전에서는 적용이 잘 안됨.
    // implementation 'org.springdoc:springdoc-openapi-ui:1.6.6'을 사용
}
