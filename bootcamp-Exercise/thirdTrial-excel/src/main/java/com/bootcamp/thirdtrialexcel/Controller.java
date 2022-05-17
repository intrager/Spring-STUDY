package com.bootcamp.thirdtrialexcel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public void excute(ArrayList<ArrayList<String>> list) throws IOException {

        Connection conn  = null;
        PreparedStatement pstmt = null;
        String query = "INSERT INTO excel (workCode, workName, company, manager, rank, category, phone) values (?, ?, ?, ?, ?, ?, ?)";

        System.out.println("총 라인 수 : "+list.size());

        try {
            conn = getConn();	//데이터베이스 연결
            pstmt = conn.prepareStatement(query);	//쿼리 설정

            for(int i=0; i<list.size(); i++) {      //매개변수로 받아온 ArrayList 의 길이만큼 반복한다.

                //읽어온 각 셀들이 자신이 생성해준 table 제약조건과 일치하지 않을 경우 SqlException이 발생한다.
                //그러한 조건이 발생하면 continue 를 해주는 부분을 추가해주면 된다.
                if(list.get(i).isEmpty()) continue;	//행에 값이 없을 경우에 그 행을 제외하고 진행

                //테이블이 utf-8 이라면 이모티콘은 utf-8 형식에 어긋나므로 content, title에서 이모티콘만 제거한다.
                Pattern emoticons = Pattern.compile("[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+");
                String temp = null;
                Matcher emoticonsMatcher=emoticons.matcher(temp);
                temp = emoticonsMatcher.replaceAll("");
                String temptitle = null;
                emoticonsMatcher=emoticons.matcher(temptitle);
                temptitle = emoticonsMatcher.replaceAll("");

                //앞의 쿼리에서 물음표에 들어갈 항목들을 순서대로 기입
                pstmt.setString(1, list.get(i).get(0));
                pstmt.setString(2, list.get(i).get(1));
                pstmt.setString(3, list.get(i).get(2));
                pstmt.setString(4, list.get(i).get(3));
                pstmt.setString(5, temptitle);
                pstmt.setString(6, temp);
                pstmt.setString(7, list.get(i).get(6));

                //update query 실행
                pstmt.executeUpdate();

                if(i%1000==0) {
                    System.out.println(i+"번 라인 쓰는 중...");
                }
            }

            System.out.println("insert를 완료했습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            if(conn  != null) try { conn.close();  } catch (SQLException e) {}
        } // DB 연결에 사용한 객체와 Query수행을 위해 사용한 객체를 닫는다.
    }

    public Connection getConn() {
        //이것도 조만간 mybatis로 바꿔야겠다 너무 느리다..
        Connection conn = null;
        String dbUrl = "jdbc:mariadb://localhost/tablename";
        String id = "root";
        String pw = "password";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.getMessage();
            System.out.println("연결되지 않았습니다.");
        } // 드라이버 연결

        try {
            conn = DriverManager.getConnection(dbUrl, id, pw);
            System.out.println("연결에 성공했습니다.");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
