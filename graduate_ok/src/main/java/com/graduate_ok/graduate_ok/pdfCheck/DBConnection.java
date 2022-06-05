package com.graduate_ok.graduate_ok.pdfCheck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://graduateok.crceatgpt03t.us-west-2.rds.amazonaws.com:3306/graduateok";
    private static final String DB_USERNAME = "graduateok";
    private static final String DB_PASSWORD = "graduateok1234";

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName(DB_DRIVER_CLASS);
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // 해당 학번/학과 전공필수 과목 (from 학과, 전공필수 테이블)
    public static List<String> getRequiredMajor(int stdId, String major) {
        String sql = "SELECT mrq_name FROM major_required_course r, major m"
                + " WHERE r.major_code = m.major_code"
                + " and r.mrq_std_id = " + stdId + " and m.major_name = '" + major + "';";
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

    // 핵심역량에 해당하는 교양 (from 교양 테이블)
    public static List<String> getCore1(String type) {
        String sql = "SELECT ky_name1 FROM ky_course WHERE ky_core = '" + type + "';";
        return getListDataFromTable(sql);
    }

    public static List<String> getCore2(String type) {
        String sql = "SELECT ky_name2 FROM ky_course WHERE ky_core = '" + type + "';";
        return getListDataFromTable(sql);
    }

    // 인재상에 해당하는 교양 (from 교양 테이블)
    public static List<String> getTalent1(String type) {
        String sql = "SELECT ky_name1 FROM ky_course WHERE ky_type = '" + type + "';";
        return getListDataFromTable(sql);
    }

    public static List<String> getTalent2(String type) {
        String sql = "SELECT ky_name2 FROM ky_course WHERE ky_type = '" + type + "';";
        return getListDataFromTable(sql);
    }

    // 교양 학점 가져오기 (from 교양 테이블)
    public static int getKyCredit(String ky_name) {
        String sql = "SELECT ky_credit FROM ky_course WHERE ky_name1 = '" + ky_name + "' or ky_name2 = '" + ky_name + "';";
        return getIntDataFromTable(sql, "ky_credit");
    }


    // DB에서 리스트 데이터 가져오기
    private static List<String> getListDataFromTable(String sql) {
        List<String> list = new ArrayList<>();

        try {
            Connection con = getCon();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
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

    // 교양 카운트 증가
    public static int updateKyCount(String ky_name) {
        String sql = "UPDATE ky_course SET ky_count = ky_count + 1 WHERE ky_name1 = '" + ky_name + "' or ky_name2 = '" + ky_name + "';";
        int result = 0;

        try {
            Connection con = getCon();
            PreparedStatement pstmt = con.prepareStatement(sql);
            result = pstmt.executeUpdate();

            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // test
    // 교양 카운트 초기화
    public static void settingKyCount0() {
        String sql = "UPDATE ky_course SET ky_count = 0;";
        try {
            Connection con = getCon();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();

            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 정보공유 게시판 수정,삭제 패스워드 확인
    public static int checkPassword(int key, String password) {
        String sql = "SELECT brd_password FROM board WHERE brd_key = " + key + ";";
        try {
            Connection con = getCon();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                if (rs.getString(1).equals(password)) return 1; // 패스워드 일치
                else return 0; // 패스워드 불일치
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // db 오류
    }
}