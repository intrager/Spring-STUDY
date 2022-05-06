package com.study.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Configuration
 * Bean을 생성할 때 Singletone으로 한 번만 생성한다. 그리고 각종 설정을 세팅한다.
 * 
 * @EnableAutoConfiguration
 * 스프링 애플리케이션 컨텍스트를 만들 때 자동으로 설정하는 기능을 켠다.
 * 사용자가 필요할 것 같은 빈을 추측해서 Application Context를 만들 때 필요한 설정을 한다.
 * 클래스 Path를 기준으로 클래스를 찾아 설정한다. 예를 들어, 클래스 Path에 tomcat-embeded.jar 파일이 있으면,
 *  TomcatEmbeddedServletContainerFactory가 있을 것이라고 추측해서 설정한다.
 *  
 * @ComponentScan
 * 지정한 위치 이하에 있는 @Component와 @Configuration이 붙은 클래스를 스캔해서 빈으로 등록한다.
 */
@SpringBootApplication
public class Example03AnnotationDiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Example03AnnotationDiApplication.class, args);
	}

}
