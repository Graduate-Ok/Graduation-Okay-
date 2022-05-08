package com.graduate_ok.graduate_ok.pdfConfirm;

import java.io.IOException;
import java.util.Arrays;

public class BasicConfirm {
    /**
     * 기본 검사 = 파일 검사 + 학점 검사
     *
     * [파일 검사]
     * 1. 업로드 된 파일이 pdf인지
     * 2. pdf가 맞다면 한신대학교 학업성적확인서 pdf 파일이 맞는지
     *
     * [학점 검사]
     * 1. 총 취득학점 검사
     * 2. 교양 학점 검사
     * 3. 전공 최소이수학점 검사
     *
     * =====이후 단계=====
     *
     * [전공필수 검사] => MajorRequiredConfirm 클래스
     * [교양필수 & 비교과 검사] => KyRequiredConfirm 클래스
     *   ㄴ19학번부터 비교과 3개 학기 이상 || 비교과 마일리지 300점 이상 (아노덴 제외)
     *   ㄴ20학번부터 마일리지 300점 이상
     *   ㄴ19학번 이후 "글쓰기의기초"는 "소프트웨어기초"로 대체 가능
     *   ㄴ19학번 이후 영어인증자는 "영어1,2" 면제
     *   ㄴ아노덴 "대학생활길잡이","사회생활길잡이","영어1,2"는 각각 "캠퍼스라이프","인문강단","Speaking English 1,2"로 대체 가능
     * [기타 교양 검사] => 미정
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

        StringBuilder failure = new StringBuilder(); // 부족한 요건 담는 StringBuffer


        // [파일 검사]
        String[] list = PdfRead.PDFRead("C:/Users/수빈/Desktop/um72_0272003_r01.pdf");
        // test
//        for (String str : list) {
//            System.out.println(str);
//        }
        System.out.println("한신대 학업성적확인서 확인 : " + checkPDF(list));

        // [기본 정보 추출]
        for (int i = 0; i < list.length; i++) {
            String line = list[i];

            // 학번 추출
            if (line.contains("학 번")) {
                studentId = Integer.parseInt(line.substring(4, 8));
            }

            // 학과 추출
            if (line.contains("부전공Ⅰ")) {
                String[] strings = list[i-1].split(" ");
                studentMajor = strings[2];
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

        }
        // test
        System.out.println("학번 : " + studentId);
        System.out.println("학과 : " + studentMajor);
        System.out.println("총 취득학점 : " + totalCredit);
        System.out.println("교양학점 : " + kyCredit);
        System.out.println("전공학점 : " + majorCredit);
        System.out.println("비교과 이수 학기 : " + nonSubject);
        System.out.println("마일리지 : " + mileage);


        // [학점 검사]
        failure.append(checkCredit(studentId, studentMajor, totalCredit, kyCredit, majorCredit));
        // test
        System.out.println("\n부족한 요건 출력\n" + failure.toString());

    }

    // [파일 검사] 한신대학교 학업성적확인서 pdf 파일이 맞는지 검사
    private static String checkPDF(String[] list) {
        String text = Arrays.toString(list);

        if (text.contains("포털>한신종합정보>성적")) {
            return "통과";
        } else {
            return "한신대학교 학업성적확인서가 아님!";
        }
    }

    // [학점 검사]
    private static StringBuffer checkCredit(int studentId, String studentMajor, int totalCredit, int kyCredit, int majorCredit) {
        StringBuffer failure = new StringBuffer();

        // 1. 졸업학점 검사
        if (totalCredit < 130) {
            failure.append("졸업학점 " + (130 - totalCredit) + " 미달\n");
        }

        // 2. 교양 학점 검사
        if (studentId > 2016) {
            // 17학번 이후 : 35~49학점
            if (!(kyCredit >= 35 && kyCredit <= 49)) {
                failure.append("교양학점 미달\n");
            }
        } else {
            // 16학번 : 35~45학점
            if (!(kyCredit >= 35 && kyCredit <= 45)) {
                failure.append("교양학점 미달\n");
            }
        }

        // 3. 전공 최소이수학점 검사
        // 해당 학과 전공최소학점 가져오기
        int majorMinCredit = DBConnection.getMajorMinCredit(studentMajor);
        // test
        System.out.println(studentMajor + " 전공 최소이수학점 : " + majorMinCredit);
        if (majorCredit >= majorMinCredit) {
            failure.append("전공학점 " + (majorMinCredit - majorCredit) + " 미달\n");
        }

        return failure;
    }
}
