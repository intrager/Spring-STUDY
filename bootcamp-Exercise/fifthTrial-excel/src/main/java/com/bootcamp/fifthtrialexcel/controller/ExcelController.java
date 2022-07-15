package com.bootcamp.fifthtrialexcel.controller;

import com.bootcamp.fifthtrialexcel.service.ExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("")
    public String excelIndex() {
        return "/excel/excelUploadPage";
    }

    @ResponseBody
    @PostMapping("/upload")
    public Map<String, String> uploadExcelToAjax(MultipartHttpServletRequest request) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        MultipartFile excelFile = request.getFile("excelFile");

        try {
            if(excelFile != null || !excelFile.isEmpty()) {
                result.put("code", "1");
                result.put("msg", "업로드 성공");

                File destFile = new File("C:\\bootcamp\\" + excelFile.getOriginalFilename());   // 파일 위치 지정
                excelFile.transferTo(destFile); // 엑셀파일 생성
                excelService.uploadExcel(destFile);   // service단 호출
                destFile.delete();  // 업로드된 엑셀파일 삭제
            } else {
                result.put("code", "0");
                result.put("msg", "업로드 실패");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
/*
java.lang.NullPointerException
	at com.bootcamp.fifthtrialexcel.controller.ExcelController.uploadExcelToAjax(ExcelController.java:35)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1067)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)
 */
