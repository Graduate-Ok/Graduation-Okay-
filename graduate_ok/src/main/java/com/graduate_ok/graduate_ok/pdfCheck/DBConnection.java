package com.graduate_ok.graduate_ok.pdfCheck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/graduate_ok";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "alstjr0236";

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName(DB_DRIVER_CLASS);
            con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // 해당 학과 전공필수 과목 (from 학과, 전공필수 테이블)
    public static List<String> getRequiredMajor(String major) {
        String sql = "SELECT mrq_name FROM major_required_course r, major m"
                + " WHERE r.major_code = m.major_code and m.major_name = '" + major + "';";
        return getListDataFromTable(sql);
    }

    // 해당 학과 졸업학점 (from 학과 테이블)
    public static int getGraduateCredit(String major) {
        String sql = "SELECT graduate_credit FROM major WHERE major_name = '" + major + "';";
        return getIntDataFromTable(sql, "graduate_credit");
    }

    // 해당 학과 전공 최소이수학점 (from 학과 테이블)
    public static int getMajorMinCredit(String major) {
        String sql = "SELECT major_min_credit FROM major WHERE major_name = '" + major + "';";
        return getIntDataFromTable(sql, "major_min_credit");
    }

    // 핵심역량 '인문' 가져오기
    public static List<String> getHumanities() {
        String sql = "SELECT ky_name1, ky_name2 FROM ky_course WHERE ky_core = '인문';";
        return getListDataFromTable(sql);
    }
    // 핵심역량 '창의융합' 가져오기
    public static List<String> getCreativeFusion() {
        String sql = "SELECT ky_name1, ky_name2 FROM ky_course WHERE ky_core = '창의융합';";
        return getListDataFromTable(sql);
    }
    //핵심역량 '글로벌' 가져오기
    public static List<String> getGlobal() {
        String sql = "SELECT ky_name1, ky_name2 FROM ky_course WHERE ky_core = '글로벌';";
        return getListDataFromTable(sql);
    }
    // 핵심역량 '리더쉽' 가져오기
    public static List<String> getReadership() {
        String sql = "SELECT ky_name1, ky_name2 FROM ky_course WHERE ky_core = '리더쉽';";
        return getListDataFromTable(sql);
    }
    // 핵심역량 '소통' 가져오기
    public static List<String> getCommunication() {
        String sql = "SELECT ky_name1, ky_name2 FROM ky_course WHERE ky_core = '소통';";
        return getListDataFromTable(sql);
    }
    // 인재상 '소통하는 지성인' 가져오기
    public static List<String> getIntelligent() {
        String sql = "SELECT ky_name1, ky_name2 FROM ky_course WHERE ky_type = '소통하는지성인';";
        return getListDataFromTable(sql);
    }

    // 인재상 '실천하는평화인' 가져오기
    public static List<String> getPeacemaker() {
        String sql = "SELECT ky_name1, ky_name2 FROM ky_course WHERE ky_type = '실천하는평화인';";
        return getListDataFromTable(sql);
    }

    // 인재상 '도전하는창의인' 가져오기
    public static List<String> getCreator() {
        String sql = "SELECT ky_name1, ky_name2 FROM ky_course WHERE ky_type = '도전하는창의인';";
        return getListDataFromTable(sql);
    }

    //교선 학점 가져오기
    public static int getKyCredit(String ky_name1) {
        String sql = "SELECT ky_credit FROM ky_course WHERE ky_name1 = '" + ky_name1 + "';";
        return getIntDataFromTable(sql, "ky_credit");

    }



    // DB에서 리스트 데이터 가져오기
    private static List<String> getListDataFromTable(String sql) {
        List<String> list = new ArrayList<>();

        try {
            Connection con = getCon();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                list.add(rs.getString(1));
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // DB에서 정수 데이터 가져오기
    private static int getIntDataFromTable(String sql, String column) {
        int data = 0;

        try {
            Connection con = getCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                data = rs.getInt(column);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}