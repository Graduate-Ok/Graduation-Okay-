/**
 * 참고용
 */
//package com.graduate_ok.graduate_ok.pdfCheck;
//
//import java.io.IOException;
//import java.util.*;
//
//
//public class KyRequiredConfirm {
//
//    private static String[] creditsCompleted = {"false", "false", "false"};
//    // 채플/기독교/성서/대생길/사생길/독토,글쓰기/영어1, 영어2/진로와상담/비교과
//    private static String[] isCompleted = {"false", "false", "false", "false", "false", "false", "false", "false"};
//
//    // 기독교 과목 리스트 (from 교양 테이블)
//    private static List<String> christian = DBConnection.getChristian();
//    // 성서 과목 리스트 (from 교양 테이블)
//    private static List<String> bible= DBConnection.getBible();
//
//    public static void main(String args[]) throws IOException, NumberFormatException {
//
//        Map<String, Integer> SubEssential = new HashMap<>();
//
//
//        for (int i = 0; i < list.length; i++) {
//            String line = list[i];
//
//
//            // 교필 전필 과목 확인
//            if ((line.startsWith("교필") || line.startsWith("전필")) && !line.endsWith("F") && !line.endsWith("NP")) {
//                line = line.replaceAll("[\r]", "");
//                line = line.replaceAll("  .5", " .5");
//                line = line.replaceAll("  ", "");
//                line = line.replaceAll("\\( ", "\\(");
//                System.out.println(line);
//
//                String[] arr = line.split(" ");
//                SubEssential.put(arr[2], SubEssential.getOrDefault(arr[2], 0) + 1);
//            }
//        }
//
//        boolean check = checkEssential(SubEssential, studentId, Integer.parseInt(mileage), nonSubject);
//        for (int i = 0; i < isCompleted.length; i++) System.out.println(isCompleted[i] + " ");
//
//    }
//
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
//
//
//}
