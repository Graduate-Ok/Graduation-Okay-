package com.graduate_ok.graduate_ok.pdfConfirm;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.graduate_ok.graduate_ok.dto.KyCourseDto;
import com.graduate_ok.graduate_ok.service.KyRequiredConfirmService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class KyRequiredConfirm {

    private static String[] creditsCompleted = {"false", "false", "false"};
    // 채플/기독교/성서/대생길/사생길/독토,글쓰기/영어1, 영어2/진로와상담/비교과
    private static String[] isCompleted = {"false", "false", "false", "false", "false", "false", "false", "false"};
    private static String[] Christian = {"기독교와문화", "기독교와인문학", "영화속의기독교", "기독교와세계종교"};
    private static String[] bible = {"성서의세계", "성서와문학", "성서와여성", "성서와예술", "성서와평화", "성서와영성", "사건으로읽는성서"};

    public static void main(String args[]) throws IOException {




        String text = PDFRead("C:/Users/수빈/Desktop/um72_0272003_r01.pdf");

        String[] list = text.split("\n");
        int studentId = 0; //학번
        String mileage = "";
        int extraCurr = 0;
        int gyoyang = 0;
        int major = 0;
        // System.out.println(text);
        Map<String, Integer> SubEssential = new HashMap<>();

        for (int i = 0; i < list.length; i++) {
            String line = list[i];
            // 학번 추출
            if (line.contains("학 번")) {
                studentId = Integer.parseInt(line.substring(4, 8));
            }

            // 마일리지 추출
            if (line.contains("마일리지")) {
                mileage = line.substring(22, 25);
                extraCurr = i - extraCurr - 1;
            }

            // 비교과 이수 학기 확인
            if (line.contains("비교과 프로그램")) {
                extraCurr = i;
            }

            // 전공, 교양 이수학점 확인
            if (line.contains("교양: ") && line.contains("전공: ")) {
                gyoyang = Integer.parseInt(line.substring(4, 6));
                major = Integer.parseInt(line.substring(11, 13));
            }


            // 교필 전필 과목 확인
            if ((line.startsWith("교필") || line.startsWith("전필")) && !line.endsWith("F") && !line.endsWith("NP")) {
                line = line.replaceAll("[\r]", "");
                line = line.replaceAll("  .5", " .5");
                line = line.replaceAll("  ", "");
                line = line.replaceAll("\\( ", "\\(");
                System.out.println(line);

                String[] arr = line.split(" ");
                SubEssential.put(arr[2], SubEssential.getOrDefault(arr[2], 0) + 1);
            }
        }

        System.out.println("한신대 문서 맞는지? : " + VeificationPDF(text));
        boolean check = checkEssential(SubEssential, studentId, Integer.parseInt(mileage), extraCurr);
        boolean check2 = checkCredits(major, gyoyang, studentId);
        for (int i = 0; i < isCompleted.length; i++) System.out.println(isCompleted[i] + " ");

    }

    private static boolean checkCredits(int major, int gyoyang, int studentId) {
        // TODO Auto-generated method stub
        if (studentId <= 2016) {
            if (major >= 72 && gyoyang >= 35 && gyoyang <= 45 && major + gyoyang >= 130) return true;
        } else {
            if (major >= 72 && gyoyang >= 35 && gyoyang <= 49 && major + gyoyang >= 130) return true;
        }
        return false;
    }

    private static boolean checkEssential(Map<String, Integer> subject, int studentId, int mileage, int numExtra) {
        // TODO Auto-generated method stub

        // 채플 이수
        if (subject.getOrDefault("채플", 0) >= 4) isCompleted[0] = "채플 : true";
        // 성서관련 과목중 1개이수 , 기독교관련 과목중 1개이수
        for (String key : subject.keySet()) {
            if (Arrays.asList(Christian).contains(key) && subject.get(key) >= 1) isCompleted[1] = "기독교 : true";
            if (Arrays.asList(bible).contains(key) && subject.get(key) >= 1) isCompleted[2] = "성서 : true";
        }

        // 대생길 or 인증상담1 이수
        if (subject.getOrDefault("대학생활길잡이", 0) >= 1 || subject.getOrDefault("인증상담1", 0) >= 1)
            isCompleted[3] = "대생길 : true";
        // 사생길 or 인증상담2 이수
        if (subject.getOrDefault("사회생활길잡이", 0) >= 1 || subject.getOrDefault("인증상담2", 0) >= 1)
            isCompleted[4] = "사생길 : true";
        // 독서와토론 & 글쓰기와기초 & 영어1 & 영어2 이수 , 2013 학번부터
        if (studentId < 2013) {
            isCompleted[5] = "NoApplicable";
        } else {
            if (subject.getOrDefault("독서와토론", 0) >= 1 && subject.getOrDefault("글쓰기의기초", 0) >= 1
                    && subject.getOrDefault("영어Ⅰ", 0) >= 1 && subject.getOrDefault("영어Ⅱ", 0) >= 1) {
                isCompleted[5] = "독토, 글쓰기, 영어1 2 : true";
            }
        }

        // 2016학번부터 진로와상담 이수
        if (studentId < 2016) {
            isCompleted[6] = "NoApplicable";
        } else {
            if (subject.getOrDefault("진로와상담", 0) >= 4) isCompleted[6] = "진로와상담 : true";
        }

        // 2017학번부터 비교과프로그램 3개 학기 이상 이수 or 마일리지 300이상
        if (studentId < 2017) {
            isCompleted[7] = "NoApplicable";
        } else {
            if (numExtra >= 3 || mileage >= 300) isCompleted[7] = "비교과 : true";
        }

        return false;
    }

    // pdf 읽기
    private static String PDFRead(String fileName) throws IOException {

        File source = new File(fileName);
        PDDocument pdfDoc = PDDocument.load(source);
        String text = new PDFTextStripper().getText(pdfDoc);

        return text;
    }

    // 문서내에 이런 단어들이 존재하는가?
    // 한신대 성적 문서가 맞나?
    private static boolean VeificationPDF(String text) {

        String str1_verify = "* 이수구분변경은 포털>한신종합정보>성적>\"\r\n" +
                "이수구분변경신청\"에서 신청하세요.\r\n";
        String str2_verify = "*최초평점평균조회 : 포털>한신종합정보>성적\r\n" +
                ">전체성적조회\r\n" + "";
        String str3_verify = "* 졸업학점, 교양및전공필수 이수(미\r\n" + "이수)현황 :\r\n" +
                "포털>한신종합정보>성적>\"교양 및 \r\n" + "전공필수 이수현황\"에서 확인하세요.\r\n" + "";

        if (text.contains(str1_verify) && text.contains(str2_verify) && text.contains(str3_verify))
            return true;

        return false;
    }

}
