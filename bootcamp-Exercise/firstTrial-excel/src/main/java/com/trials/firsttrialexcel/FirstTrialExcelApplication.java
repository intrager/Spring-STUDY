package com.trials.firsttrialexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstTrialExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstTrialExcelApplication.class, args);
    }

}

/**
 * 첫 째 시도 - https://jhhan009.tistory.com/67
 * org.springframework.http.converter.HttpMessageNotWritableException: No converter for [class java.util.LinkedHashMap] with preset Content-Type 'ms-vnd/excel']
 *     --> @GETMapping 옆에 produces = MediaType.APPLICATION_JSON_VALUE 추가하여 Content-Type 문제를 해결한 듯 싶었지만
 * Cannot render error page for request [/excel/download] as the response has already been committed. As a result, the response may have the wrong status code.
 * 이 오류는 해결하지 못 함... 레퍼런스에서도 해결법을 못 찾고 있어서 다른 레퍼런스를 참고하기로 함
 *
 */