//package mytest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ModelUse {
//	
//	public static void main(String[] args) {
//		Map<String, String> model = new HashMap<>();	// 요청이 오면 Map 변수를 만듦
//		String sReturn = root(model); // 리퀘스트 맵핑에 의해 메서드를 호출하면서 매서드에 객체 변수를 넘겨줌
//		// 컨테이너는 17줄에서 리턴받은 정보를 이용하여 뷰를 출력하는 메서드를 호출함
//		// 이때, 페이지 이름과 페이지에서 사용할 정보를 파라미터로 모두 넘겨줌.
//		printData(sReturn, model);	 
//	}
//	
//	public static String root(Map model) {	// 리퀘스트맵핑의 호출에 의해 실행된 메서드는 
//		model.put("name1", "홍길동");	// 파라미터로 받은 model에 데이터를 원하는 만큼 넣고
//		model.put("name2", "전우치");
//		return "Hello";		// 사용자에게 보여줄 JSP 페이지의 이름을 리턴한다.
//	}
//	
//	public static void printData(String s, Map model) {
//		String str1 = (String)model.get("name1");
//		System.out.println(str1);
//		System.out.println("WEB-INF/views/" + s + ".jsp");
//	}
//}
