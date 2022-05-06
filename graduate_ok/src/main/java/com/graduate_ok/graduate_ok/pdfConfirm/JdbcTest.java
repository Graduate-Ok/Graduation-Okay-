package com.graduate_ok.graduate_ok.pdfConfirm;

import com.graduate_ok.graduate_ok.dto.KyCourseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {
    // jdbc
    public static void main(String[] args) {
        Connection conn = DBConnectionMaria.getConnection();
        ArrayList<KyCourseDto> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<String> christian = new ArrayList<>();

        try {
            con = DBConnectionMaria.getConnection();
            StringBuffer sql = new StringBuffer();

            sql.append("SELECT ky_name1 FROM ky_course WHERE ky_name1 LIKE '%기독교%'");
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();	//쿼리 실행

            int index = 1;
            while(rs.next()) {
                index = 1;
                christian.add(rs.getString(index++));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(con != null) con.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }

        for (String str : christian) {
            System.out.println(str);
        }

    }
}

