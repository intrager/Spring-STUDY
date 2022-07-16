package com.study.ch2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

// @Controller + @RequestMapping == @WebServlet
@WebServlet("/myDispatcherServlet")
public class MyDispatcherServlet extends HttpServlet {	// http://localhost:8080/ch2/myDispatcherServlet?year=2022&month=7&day=16
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map map = request.getParameterMap();
		Model model = null;
		String viewName = "";
	
		try {
			Class glas = Class.forName("com.study.ch2.YoilTellerMVC");
			Object obj = glas.newInstance();
			
			// 1. main 메서드의 정보를 얻는다.
			Method main = glas.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
			// 2. main 메서드의 매개변수 목록(paramArr)을 읽어서 메서드 호출에 사용할 인자 목록(argArr)을 만든다.
			Parameter[] paramArr = main.getParameters();
			Object[] argArr = new Object[main.getParameterCount()];
			
			for(int i = 0; i < paramArr.length; i++) {
				String paramName = paramArr[i].getName();
				Class paramType = paramArr[i].getType();
				Object value = map.get(paramName);
				
				// paramType 중에서 Model이 있으면 생성 및 저장
				if(paramType == Model.class) {
					argArr[i] = model = new BindingAwareModelMap();
				} else if (paramType == HttpServletRequest.class) {
					argArr[i] = request;
				} else if (paramType == HttpServletResponse.class) {
					argArr[i] = response;
				} else if (value != null) {	// map에 paramName이 있으면
					// value와 parameter의 타입을 비교해서 다르면 변환 후 저장
					String strValue = ((String[]) value)[0];	// getParameterMap()에서 꺼낸 value는 String 배열이므로 변환이 필요함
					argArr[i] = convertTo(strValue, paramType);
				}
			}
			
			// 3. Controller의 main() 를 호출 - YoilTellerMVC.main(int year, int month, int day, Model model)
			viewName = (String)main.invoke(obj, argArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 4. 텍스트 파일을 이용한 rendering
		render(model, viewName, response);
	}	// main

	private Object convertTo(Object value, Class type) {
		if(type == null || value == null || type.isInstance(value))	// 타입이 같으면 그대로 반환
			return value;
		
		// 타입이 다르면 변환해서 반환함
		if(String.class.isInstance(value) && type == int.class) {	// String -> int
			return Integer.valueOf((String)value);
		} else if(String.class.isInstance(value) && type == double.class) {	// String -> double
			return Double.valueOf((String)value);
		}
		
		return value;
	}
	
	private void render(Model model, String viewName, HttpServletResponse response) throws IOException {
		String result = "";
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		// 1. 뷰의 내용을 한 줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File(getResolvedViewName(viewName)), "utf-8");
		
		while(sc.hasNextLine())
			result += sc.nextLine() + System.lineSeparator();
		
		// 2. model을 map으로 변환
		Map map = model.asMap();
		
		// 3. key를 하나씩 읽어서 template의 ${key}를 value로 바꾼다.
		Iterator iter = map.keySet().iterator();
		
		while(iter.hasNext()) {
			String key = (String)iter.next();
			
			// 4. replace()를 이용하여 key를 value로 치환한다.
			result = result.replace("${" + key + "}", map.get(key) + "");
		}
		
		// 5. 렌더링 결과를 출력한다.
		out.println(result);
	}

	private String getResolvedViewName(String viewName) {
		return getServletContext().getRealPath("/WEB-INF/views") + "/" + viewName + ".jsp";
	}
}
