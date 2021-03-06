package com.study.ch2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall2 {
	public static void main(String[] args) throws Exception {
		
		// 1. YoilTellerMVC 객체를 생성
		Class glas = Class.forName("com.study.ch2.YoilTellerMVC");
		Object obj = glas.newInstance();
		
		// 2. main 메서드 정보를 가져온다
		Method main = glas.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
	
		// 3. Model 생성
		Model model = new BindingAwareModelMap();
		System.out.println("[before] model=" + model);
		
		// 4. main 메서드 호출
		// Reflection API를 통한 호출
		String viewName = (String)main.invoke(obj, new Object[] { 2022, 7, 16, model });
		System.out.println("viewName=" + viewName);
		
		// Model의 내용을 출력
		System.out.println("[after] model=" + model);
		
		// 텍스트 파일을 이용한 rendering
		render(model, viewName);
	}	// main
	
	static void render(Model model, String viewName) throws IOException {
		String result = "";
		
		// 1. 뷰의 내용을 한 줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
		
		while(sc.hasNextLine()) {
			result += sc.nextLine() + System.lineSeparator();
		}
		// 2. model을 map으로 변환
		Map map = model.asMap();
	
		// 3. key를 하나씩 읽어서 template의 ${key}를 value로 바꾼다.
		Iterator iter = map.keySet().iterator();
		
		while(iter.hasNext()) {
			String key = (String)iter.next();
			
			// 4. replace()로 key를 value로 치환한다.
			result = result.replace("${" + key + "}", "" + map.get(key));
		}
		
		// 5. 렌더링 결과를 출력한다.
		System.out.println(result);
	}
}
