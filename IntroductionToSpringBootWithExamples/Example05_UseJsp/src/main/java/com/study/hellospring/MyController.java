package com.study.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "JSP in Gradle";
	}
	
	/**
	 * @ResponseBody 어노테이션이 없기 때문에 
	 * 20라인에서 리턴하는 이름에 properties에서 지정한 접두어와 접미어를 붙여서
	 * 실제 이 폴더에 가서 해당 파일을 찾아 실행하고, 그 결과를 리턴하게 된다.
	 */
	@RequestMapping("/test1")	// localhost:8081/test1
	public String test1() {
		return "test1";			// 실제 호출될 /WEB-INF/views/test1.jsp
	}
	
	@RequestMapping("/test2")	// localhost:8081/test2
	public String test2() {
		return "sub/test2";		// 실제 호출될 /WEB-INF/views/sub/test2.jsp
	}
	
}
