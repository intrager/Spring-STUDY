package com.bootcamp.thirdtrialexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ThirdTrialExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdTrialExcelApplication.class, args);

        System.out.println("main 시작");
        UpdateDatabaseFromExcel eamCsvLoad = new UpdateDatabaseFromExcel();  //객체 생성
        String Location = "C:\\bootcamp\\hansol.xlsx";		// 파일의 위치 및 이름
        ArrayList<ArrayList<String>> list = eamCsvLoad.readFilter(Location);	//파일에서 각 셀들을 읽어서 Arraylist에 저장
        System.out.println("파일 update 시작");
        eamCsvLoad.excute(list);	//list에 저장되 있는 데이터들을 데이터베이스에 업로드하는 함수 시작

    }

}
