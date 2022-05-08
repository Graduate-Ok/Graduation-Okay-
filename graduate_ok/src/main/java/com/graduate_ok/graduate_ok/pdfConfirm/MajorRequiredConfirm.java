package com.graduate_ok.graduate_ok.pdfConfirm;

import java.util.List;

public class MajorRequiredConfirm {

    // 전공필수 검사
    // 해당 학번 & 학과에 해당하는 전공필수 과목
    private static List<String> majors = DBConnection.getRequiredMajor("컴퓨터공학부");
}
