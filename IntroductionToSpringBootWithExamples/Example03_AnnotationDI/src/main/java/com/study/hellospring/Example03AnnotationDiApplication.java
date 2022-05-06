package com.study.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Configuration
 * Bean�� ������ �� Singletone���� �� ���� �����Ѵ�. �׸��� ���� ������ �����Ѵ�.
 * 
 * @EnableAutoConfiguration
 * ������ ���ø����̼� ���ؽ�Ʈ�� ���� �� �ڵ����� �����ϴ� ����� �Ҵ�.
 * ����ڰ� �ʿ��� �� ���� ���� �����ؼ� Application Context�� ���� �� �ʿ��� ������ �Ѵ�.
 * Ŭ���� Path�� �������� Ŭ������ ã�� �����Ѵ�. ���� ���, Ŭ���� Path�� tomcat-embeded.jar ������ ������,
 *  TomcatEmbeddedServletContainerFactory�� ���� ���̶�� �����ؼ� �����Ѵ�.
 *  
 * @ComponentScan
 * ������ ��ġ ���Ͽ� �ִ� @Component�� @Configuration�� ���� Ŭ������ ��ĵ�ؼ� ������ ����Ѵ�.
 */
@SpringBootApplication
public class Example03AnnotationDiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Example03AnnotationDiApplication.class, args);
	}

}
