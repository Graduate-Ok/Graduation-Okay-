package com.graduate_ok.graduate_ok.pdfCheck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/graduate_ok";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "0320";

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

    // 기독교 과목 (from 교양 테이블)
    public static List<String> getChristian() {
        String sql = "SELECT ky_name1 FROM ky_course WHERE ky_name1 LIKE '%기독교%';";
        return getListDataFromTable(sql);
    }

    // 성서 과목 (from 교양 테이블)
    public static List<String> getBible() {
        String sql = "SELECT ky_name1 FROM ky_course WHERE ky_name1 LIKE '%성서%';";
        return getListDataFromTable(sql);
    }

    // 해당 학과 전공필수 과목 (from 학과, 전공필수 테이블)
    public static List<String> getRequiredMajor(String major) {
        String sql = "SELECT mrq_name FROM major_required_course r, major m"
                + " WHERE r.major_code = m.major_code and m.major_name = '" + major + "';";
        return getListDataFromTable(sql);
    }

    // 해당 학과 전공 최소이수학점 (from 학과 테이블)
    public static int getMajorMinCredit(String major) {
        String sql = "SELECT major_min_credit FROM major WHERE major_name = '" + major + "';";
        return getIntDataFromTable(sql);
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
    private static int getIntDataFromTable(String sql) {
        int data = 0;

        try {
            Connection con = getCon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                data = rs.getInt("major_min_credit");
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
