//package com.study.ch3;
//
//import java.util.Arrays;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//
//// @Component("engine")
//class Engine{}	// <bean id="engine" class="com.study.ch3.Engine />
//@Component class SuperEngine extends Engine{}
//@Component class TurboEngine extends Engine{}
//
//@Component class Door{}
//@Component
//class Car{
//	@Value("red") String color;
//	@Value("100") int oil;
//	
////	@Autowired // @Autowired는 타입에 맞는 거 하나만 넣음, byType --> engine, superEngine, turboEngine
////	@Qualifier("superEngine")
//	@Resource(name = "superEngine")	// byName, 근데 얘는 이름이 바뀌면 번거롭게 고쳐야 함
//	Engine engine;	
//	@Autowired Door[] doors;	// @Autowired는 타입으로 먼저 검색, 여러개면 이름으로 검색
//	
//	// constructor-arg 없는 bean 태그가 있을 때 필요함 -> 기본 생성자를 이용하여 객체를 만듦
//	public Car() {}	
//	public Car(String color, int oil, Engine engine, Door[] doors) {
//		this.color = color;
//		this.oil = oil;
//		this.engine = engine;
//		this.doors = doors;
//	}
//	
//	// xml에서 property 쓸려면 setter 있어야 함 
//	public void setColor(String color) {
//		this.color = color;
//	}
//	public void setOil(int oil) {
//		this.oil = oil;
//	}
//	public void setEngine(Engine engine) {
//		this.engine = engine;
//	}
//	public void setDoors(Door[] doors) {
//		this.doors = doors;
//	}
//	
//	@Override
//	public String toString() {
//		return "Car [color=" + color + ", oil=" + oil + ", engine=" + engine + ", doors=" + Arrays.toString(doors)
//				+ "]";
//	}
//	
//	
//}
//
//public class SpringDITest {
//	public static void main(String[] args) {
//		ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//	//	Car car = (Car) ac.getBean("car");	// byName	// 아래 문장과 같음
//		Car car = ac.getBean("car", Car.class);	// byName	// 위 문장과 같음
////		Car car2 = (Car) ac.getBean(Car.class);	// byType	// 싱글톤이기 때문에 getBean하면 위 문장과 같은 객체가 반환됨
////		
////		Engine engine = (Engine) ac.getBean("superEngine");	// byName
//////		Engine engine = (Engine) ac.getBean(Engine.class);	// byType
////		Door door = (Door) ac.getBean("door");
//		
//		
////		car.setColor("red");
////		car.setOil(100);
////		car.setEngine(engine);
////		car.setDoors(new Door[] {ac.getBean("door", Door.class), (Door) ac.getBean("door")});
//		System.out.println("car = " + car);
//	}
//}
