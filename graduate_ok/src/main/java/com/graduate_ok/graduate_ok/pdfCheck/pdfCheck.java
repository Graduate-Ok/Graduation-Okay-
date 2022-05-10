package com.graduate_ok.graduate_ok.pdfCheck;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pdfCheck {
    /**
     * 검사 순서
     *
     * [파일 검사] 한신대학교 학업성적확인서 pdf 파일이 맞는지 검사
     *
     * [학점 검사]
     * 1. 총 취득학점 검사
     * 2. 교양 학점 검사
     * 3. 전공 최소이수학점 검사
     *
     * [전공필수 검사] 해당 학과의 전공필수 다 들었는지 검사
     *
     * [교양필수 & 비교과 검사]
     *   ㄴ19학번부터 비교과 3개 학기 이상 || 비교과 마일리지 300점 이상 (아노덴 제외)
     *   ㄴ20학번부터 마일리지 300점 이상
     *   ㄴ19학번 이후 "글쓰기의기초"는 "소프트웨어기초"로 대체 가능
     *   ㄴ19학번 이후 영어인증자는 "영어1,2" 면제
     *   ㄴ아노덴 "대학생활길잡이","사회생활길잡이","영어1,2"는 각각 "캠퍼스라이프","인문강단","Speaking English 1,2"로 대체 가능
     *
     * [기타 교양 검사]
     *   ㄴ19학번 교양 인재상별 5학점 이상
     *   ㄴ20학번 이후 교양 핵심역량별 1과목 이상
     *
     * ! 고려 안 한 대상 !
     *   1) 복수전공
     *   2) 편입생
     */

    public static void main(String[] args) throws IOException {

        int studentId = 0; // 학번
        String studentMajor = ""; // 학과

        int totalCredit = 0; // 총 취득학점
        int kyCredit = 0; // 교양 학점
        int majorCredit = 0; // 전공 학점

        int nonSubject = 0; // 비교과 이수 학기
        int mileage = 0; // 비교과 마일리지

        Map<String, Integer> requiredMajor = new HashMap<>(); // 전필 담는 Map
        Map<String, Integer> requiredKy = new HashMap<>(); // 교필 담는 Map

        // 부족한 요건 담는 StringBuilder
        StringBuilder failure = new StringBuilder();


        /**
         * 파일 읽어오기 + [파일 검사]
         */
        String[] list = PdfRead("C:/Users/수빈/Desktop/um72_0272003_r01.pdf");
        // test
//        for (String str : list) {
//            System.out.println(str);
//        }
        System.out.println("한신대 학업성적확인서 확인 : " + checkPDF(list));


        /**
         * 기본 정보 추출
         */
        for (int i = 0; i < list.length; i++) {
            String line = list[i];

            // 학번 추출
            if (line.contains("학 번")) {
                studentId = Integer.parseInt(line.substring(4, 8));
            }

            // 학과 추출
            if (line.contains("부전공Ⅰ")) {
                String[] strings = list[i-1].split(" ");
                studentMajor = strings[2].substring(0, strings[2].length() - 1);
            }


            // 총 취득학점 추출
            if (line.contains("총 취득학점")) {
                totalCredit = Integer.parseInt(line.substring(7, line.length() - 1));
            }

            // 교양, 전공 이수학점 확인
            if (line.contains("교양: ") && line.contains("전공: ")) {
                kyCredit = Integer.parseInt(line.substring(4, 6));
                majorCredit = Integer.parseInt(line.substring(11, 13));
            }


            // 비교과 이수 학기 카운트
            if (line.contains("학기 인정")) {
                nonSubject++;
            }

            // 마일리지 추출
            if (line.contains("마일리지")) {
                mileage = Integer.parseInt(line.substring(22, line.length() - 1));
            }

            // 수강한 전필 과목 추출
            requiredMajor = extractRequired(line, "전필");

            // 수강한 교필 과목 추출
            requiredKy = extractRequired(line, "교필");

        }
        // test
        System.out.println("학번 : " + studentId);
        System.out.println("학과 : " + studentMajor);
        System.out.println("총 취득학점 : " + totalCredit);
        System.out.println("교양학점 : " + kyCredit);
        System.out.println("전공학점 : " + majorCredit);
        System.out.println("비교과 이수 학기 : " + nonSubject);
        System.out.println("마일리지 : " + mileage);


        /**
         * [학점 검사]
         */
        failure.append(checkCredit(studentId, studentMajor, totalCredit, kyCredit, majorCredit));
        // test
        System.out.println("\n부족한 요건 출력\n" + failure.toString());


        /**
         * [전공필수 검사]
         * xxxxx 공사 중 xxxxx
         */
        failure.append(checkRequiredMajor(studentMajor));

        /**
         * [교양필수 검사]
         * xxxxx 공사 예정 xxxxx
         */
        //boolean check = checkEssential(SubEssential, studentId, Integer.parseInt(mileage), nonSubject);

    }

    /**
     * 전필,교필 과목 추출
     */
    private static Map<String, Integer> extractRequired(String line, String type) {
        Map<String, Integer> SubEssential = new HashMap<>();

        if (line.startsWith(type) && !line.endsWith("F") && !line.endsWith("NP")) {
            line = line.replaceAll("[\r]", "");
            line = line.replaceAll("  .5", " .5");
            line = line.replaceAll("  ", "");
            line = line.replaceAll("\\( ", "\\(");
            System.out.println(line);

            String[] arr = line.split(" ");
            SubEssential.put(arr[2], SubEssential.getOrDefault(arr[2], 0) + 1);
        }

        return SubEssential;
    }

    /**
     * pdf 읽어오기
     */
    private static String[] PdfRead(String fileName) throws IOException {

        File source = new File(fileName);
        PDDocument pdfDoc = PDDocument.load(source);
        String text = new PDFTextStripper().getText(pdfDoc);
        String[] pdf = text.split("\n");

        return pdf;
    }


    /**
     * [파일 검사] 한신대학교 학업성적확인서 pdf 파일이 맞는지 검사
     */
    private static String checkPDF(String[] list) {
        String text = Arrays.toString(list);

        if (text.contains("포털>한신종합정보>성적")) {
            return "통과";
        } else {
            return "한신대학교 학업성적확인서가 아님!";
        }
    }


    /**
     * [학점 검사]
     * 1. 총 취득학점 검사
     * 2. 교양 학점 검사
     * 3. 전공 최소이수학점 검사
     */
    private static StringBuffer checkCredit(int studentId, String studentMajor, int totalCredit, int kyCredit, int majorCredit) {
        StringBuffer failure = new StringBuffer();

        //TEST
//        System.out.println("\n=== checkCredit 파라미터 체크 ===");
//        System.out.println("학번 : " + studentId);
//        System.out.println("전공 : " + studentMajor);
//        System.out.println("총 취득학점 : " + totalCredit);
//        System.out.println("교양학점 : " + kyCredit);
//        System.out.println("전공학점 : " + majorCredit);

        // 1. 졸업학점 검사
        if (totalCredit < 130) {
            failure.append("졸업학점 " + (130 - totalCredit) + " 미달\n");
        }

        // 2. 교양 학점 검사
        if (studentId <= 2016) {
            // 16학번 이전 : 35~45학점
            if (!(kyCredit >= 35 && kyCredit <= 45)) {
                failure.append("교양학점 미달\n");
            }
        } else {
            // 17학번 이후 : 35~49학점
            if (!(kyCredit >= 35 && kyCredit <= 49)) {
                failure.append("교양학점 미달\n");
            }
        }

        // 3. 전공 최소이수학점 검사
        // 해당 학과 전공최소학점 가져오기
        int majorMinCredit = DBConnection.getMajorMinCredit(studentMajor);
        // test
        //System.out.println("전공 '" + studentMajor + "' 최소이수학점 : " + majorMinCredit);
        if (majorMinCredit > majorCredit) {
            failure.append("전공학점 " + (majorMinCredit - majorCredit) + " 미달\n");
        }

        return failure;
    }


    /**
     * [전공필수 검사]
     * xxxxx 공사 중 xxxxx
     */
    private static StringBuffer checkRequiredMajor(String studentMajor) {
        StringBuffer failure = new StringBuffer();

        // 해당 학과에서 전공필수 과목 가져오기
        //List<String> requiredMajors = DBConnection.getRequiredMajor(studentMajor);

        // pdf 파일과 비교

        // 안 들은 과목 failure에 추가 "[전공필수]ㅇㅇㅇ 미수강\n"

        return failure;
    }


    /**
     * [교양필수 검사]
     * xxxxx 공사 예정 xxxxx
     */
//    private static boolean checkEssential(Map<String, Integer> subject, int studentId, int mileage, int numExtra) {
//        // 채플 이수
//        if (subject.getOrDefault("채플", 0) >= 4) isCompleted[0] = "채플 : true";
//        // 성서관련 과목중 1개이수 , 기독교관련 과목중 1개이수
//        for (String key : subject.keySet()) {
//            if (christian.contains(key) && subject.get(key) >= 1) isCompleted[1] = "기독교 : true";
//            if (bible.contains(key) && subject.get(key) >= 1) isCompleted[2] = "성서 : true";
//        }
//
//        // 대생길 or 인증상담1 이수
//        if (subject.getOrDefault("대학생활길잡이", 0) >= 1 || subject.getOrDefault("인증상담1", 0) >= 1)
//            isCompleted[3] = "대생길 : true";
//        // 사생길 or 인증상담2 이수
//        if (subject.getOrDefault("사회생활길잡이", 0) >= 1 || subject.getOrDefault("인증상담2", 0) >= 1)
//            isCompleted[4] = "사생길 : true";
//        // 독서와토론 & 글쓰기와기초 & 영어1 & 영어2 이수 , 2013 학번부터
//        if (studentId < 2013) {
//            isCompleted[5] = "NoApplicable";
//        } else {
//            if (subject.getOrDefault("독서와토론", 0) >= 1 && subject.getOrDefault("글쓰기의기초", 0) >= 1
//                    && subject.getOrDefault("영어Ⅰ", 0) >= 1 && subject.getOrDefault("영어Ⅱ", 0) >= 1) {
//                isCompleted[5] = "독토, 글쓰기, 영어1 2 : true";
//            }
//        }
//
//        // 2016학번부터 진로와상담 이수
//        if (studentId < 2016) {
//            isCompleted[6] = "NoApplicable";
//        } else {
//            if (subject.getOrDefault("진로와상담", 0) >= 4) isCompleted[6] = "진로와상담 : true";
//        }
//
//        // 2017학번부터 비교과프로그램 3개 학기 이상 이수 or 마일리지 300이상
//        if (studentId < 2017) {
//            isCompleted[7] = "NoApplicable";
//        } else {
//            if (numExtra >= 3 || mileage >= 300) isCompleted[7] = "비교과 : true";
//        }
//
//        return false;
//    }
}
