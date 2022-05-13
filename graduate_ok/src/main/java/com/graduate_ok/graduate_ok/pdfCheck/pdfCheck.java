package com.graduate_ok.graduate_ok.pdfCheck;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

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
     * ㄴ17학번부터 비교과 3개 학기 이상 || 비교과 마일리지 300점 이상 (아노덴 제외)
     * ㄴ20학번부터 마일리지 300점 이상
     * ㄴ19학번 이후 "글쓰기의기초"는 소프트웨어교과목으로 대체 가능
     * ㄴ19학번 이후 영어인증자는 "영어1,2" 면제
     * ㄴ아노덴 "대학생활길잡이","사회생활길잡이","영어1,2"는 각각 "캠퍼스라이프","인문강단","Speaking English 1,2"로 대체 가능
     *
     * [기타 교양 검사]
     * ㄴ19학번 교양 인재상별 5학점 이상
     * ㄴ20학번 이후 교양 핵심역량별 1과목 이상
     *
     * ! 고려 안 한 대상 !
     * 1) 복수전공
     * 2) 부전공
     * 3) 편입생
     */

    public static void main(String[] args) throws IOException {

        int studentId = 0; // 학번
        String studentMajor = ""; // 학과

        int totalCredit = 0; // 총 취득학점
        int kyCredit = 0; // 교양 학점
        int majorCredit = 0; // 전공 학점

        List<String> requiredMajor = new ArrayList<>(); // 전필 담는 List
        List<String> requiredKy = new ArrayList<>(); // 교필 담는 List
        List<String> allKy = new ArrayList<>(); // 모든 교양 담는 List

        int nonSubject = 0; // 비교과 이수 학기
        int mileage = 0; // 비교과 마일리지
        boolean engCertification = false; // 영어인증자 (19학번 이후 영어인증자는 "영어1,2" 면제)

        // 부족한 요건 담는 StringBuilder
        StringBuilder failure = new StringBuilder();


        /**
         * 파일 읽어오기 + [파일 검사]
         */
        String[] list = PdfRead("C:\\springboot\\um72_0272003_r01.pdf");
        // test
        for (String str : list) {
            System.out.println(str);
        }
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
                String[] strings = list[i - 1].split(" ");
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

            // 수강한 전필 과목 추출
            if (line.startsWith("전필") && !line.contains("F") && !line.contains("NP")) {
                String[] strings = line.split(" ");
                requiredMajor.add(strings[2]);
            }

            // 수강한 교필 과목 추출
            if (line.startsWith("교필") && !line.contains("NP")) {
                String[] strings = line.split("\\s+");
                if (!(strings[4].contains("F"))) {
                    requiredKy.add(strings[2]);
                }
            }
            if (line.startsWith("교선") || line.startsWith("교필") && !line.contains("F") && !line.contains("NP")) {
                String[] strings = line.split(" ");
                allKy.add(strings[2]);
            }

            // 비교과 이수 학기 카운트
            if (line.contains("학기 인정")) {
                nonSubject++;
            }

            // 마일리지 추출
            if (line.contains("마일리지")) {
                mileage = Integer.parseInt(line.substring(22, line.length() - 1));
            }

            // 영어인증자 추출
            if (line.contains("영어인증")) {
                engCertification = true;
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
        System.out.println("\n===수강한 전필 과목===");
        for (String str : requiredMajor) {
            System.out.println(str);
        }
        System.out.println("\n===수강한 교필 과목===");
        for (String str : requiredKy) {
            System.out.println(str);
        }
        System.out.println("\n===수강한 모든 교양===");
        for (String str : allKy) {
            System.out.println(str);
        }


        /**
         * [학점 검사]
         */
        failure.append(checkCredit(studentId, studentMajor, totalCredit, kyCredit, majorCredit));


        /**
         * [전공필수 검사]
         */
        failure.append(checkRequiredMajor(studentMajor, requiredMajor));

        /**
         * [교양필수 검사]
         */
        failure.append(checkRequiredKy(studentId, studentMajor, nonSubject, mileage, engCertification, requiredKy));

        /**
         * [핵심역량] 검사
         */
        failure.append(checkCoreKy(studentId, allKy));

        /**
         * [소통하는지성인] 검사
         */
        failure.append(checkIntelligent(studentId,allKy));
        /**
         * [실천하는평화인] 검사
         */
        failure.append(checkPeacemaker(studentId,allKy));
        /**
         * [도전하는창의인] 검사
         */
        failure.append(checkCreator(studentId,allKy));

        // test
        System.out.println("\n===부족한 요건 출력===\n" + failure.toString());



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
            return "통과\n";
        } else {
            return "한신대학교 학업성적확인서가 아님!\n";
        }
    }


    /**
     * [학점 검사]
     */
    private static StringBuffer checkCredit(int studentId, String studentMajor, int totalCredit, int kyCredit, int majorCredit) {
        StringBuffer failure = new StringBuffer();

        // 1. 졸업학점 검사
        // 해당 학과 졸업학점 가져오기
        int graduateCredit = DBConnection.getGraduateCredit(studentMajor);
        if (graduateCredit > totalCredit) {
            failure.append("졸업학점 " + (graduateCredit - totalCredit) + "학점 미달\n");
        }

        /**
         * xxxxx 공사 예정 xxxxx
         */
        // 2. 교양 학점 검사
        if (studentId <= 2016) {
            // 16학번 이전 : 35~45학점
            if (!(kyCredit >= 35 && kyCredit <= 45)) {
                failure.append("교양학점 미달 또는 초과\n");
            }
        } else {
            // 17학번 이후 : 35~49학점
            if (!(kyCredit >= 35 && kyCredit <= 49)) {
                failure.append("교양학점 미달 또는 초과\n");
            }
        }

        // 3. 전공 최소이수학점 검사
        // 해당 학과 전공최소학점 가져오기
        int majorMinCredit = DBConnection.getMajorMinCredit(studentMajor);
        if (majorMinCredit > majorCredit) {
            failure.append("전공학점 " + (majorMinCredit - majorCredit) + "학점 미달\n");
        }

        return failure;
    }


    /**
     * [전공필수 검사]
     */
    private static StringBuffer checkRequiredMajor(String studentMajor, List<String> requiredMajor) {
        StringBuffer failure = new StringBuffer();

        // 해당 학과에서 전공필수 과목 가져오기
        List<String> requiredMajors = DBConnection.getRequiredMajor(studentMajor);

        // 들어야 할 전필과 학생이 들은 전필 비교
        Collection<String> std = requiredMajor;
        requiredMajors.removeAll(std);

        for (String str : requiredMajors) {
            failure.append("전공필수 '" + str + "' 미수강\n");
        }

        return failure;
    }


    /**
     * [교양필수 검사]
     */
    private static StringBuffer checkRequiredKy(int studentId, String studentMajor, int nonSubject, int mileage, boolean engCertification, List<String> requiredKy) {
        StringBuffer failure = new StringBuffer();

        int countCP = 0; // 채플 카운트
        int christian = 0; // 기독교 카운트
        int bible = 0; // 성서 카운트
        int collegeGuide = 0; // 대생길 카운트
        int socialGuide = 0; // 사생길 카운트
        int readDebate = 0; // 독토 카운트
        int writing = 0; // 글기 카운트
        int eng1 = 0; // 영어1
        int eng2 = 0; // 영어2
        int counseling = 0; // 진로와상담 카운트

        for (int i = 0; i < requiredKy.size(); i++) {
            String line = requiredKy.get(i);

            // 채플 검사
            if (line.equals("채플")) countCP++;

            // 기독교 과목 검사
            if (line.contains("기독교")) christian++;

            // 성서 과목 검사
            if (line.contains("성서")) bible++;

            // 대학생활길잡이 검사 (아노덴 '캠퍼스라이프' 대체)
            if (line.equals("대학생활길잡이") || line.equals("캠퍼스라이프")) collegeGuide++;

            // 사회생활길잡이 검사 (아노덴 '인문강단' 대체)
            if (line.equals("사회생활길잡이") || line.equals("인문강단")) socialGuide++;

            // 독서와토론 검사
            if (line.equals("독서와토론")) readDebate++;

            // 글쓰기의기초 검사 (19학번 이후 소프트웨어교과목으로 대체 가능)
            if (line.equals("글쓰기의기초") || line.contains("소프트웨어")) writing++;

            // 영어Ⅰ,Ⅱ 검사 (아노덴 'Speaking EnglishⅠ,Ⅱ' 대체 / 19학번 이후 영어인증자 면제)
            if (line.equals("영어Ⅰ") || line.equals("EnglishⅠ") || engCertification) eng1++;
            if (line.equals("영어Ⅱ") || line.equals("EnglishⅡ") || engCertification) eng2++;

            // 진로와상담 검사
            if (line.equals("진로와상담")) counseling++;
        }

        // 채플 검사
        if (countCP < 4) failure.append("교양필수 '채플' " + (4 - countCP) + "회 미수강\n");

        // 기독교 과목 검사
        if (christian < 1) failure.append("교양필수 '기독교 관련 과목' 미수강\n");

        // 성서 과목 검사
        if (bible < 1) failure.append("교양필수 '성서 관련 과목' 미수강\n");

        // 대학생활길잡이 검사 (아노덴 '캠퍼스라이프' 대체)
        if (collegeGuide < 1) {
            if (studentMajor.contains("아노덴")) {
                failure.append("교양필수 '캠퍼스라이프' 미수강\n");
            } else {
                failure.append("교양필수 '대학생활길잡이' 미수강\n");
            }
        }

        // 사회생활길잡이 검사 (아노덴 '인문강단' 대체)
        if (socialGuide < 1) {
            if (studentMajor.contains("아노덴")) {
                failure.append("교양필수 '인문강단' 미수강\n");
            } else {
                failure.append("교양필수 '사회생활길잡이' 미수강\n");
            }
        }

        // 독서와토론 검사
        if (readDebate < 1) failure.append("교양필수 '독서와토론' 미수강\n");

        // 글쓰기의기초 검사 (19학번 이후 소프트웨어 과목으로 대체 가능)
        if (writing < 1) {
            if (studentId >= 2019) failure.append("교양필수 '글쓰기의기초' 또는 '소프트웨어교과목' 미수강\n");
            else failure.append("교양필수 '글쓰기의기초' 미수강\n");
        }

        // 영어Ⅰ,Ⅱ 검사 (아노덴 'Speaking EnglishⅠ,Ⅱ' 대체 / 19학번 이후 영어인증자 면제)
        if (eng1 < 1) {
            if (studentMajor.contains("아노덴")) {
                failure.append("교양필수 'Speaking EnglishⅠ' 미수강");
            } else {
                failure.append("교양필수 '영어Ⅰ' 미수강");
            }
            if (studentId >= 2019) {
                failure.append(" 또는 영어인증 미인증\n");
            } else {
                failure.append("\n");
            }
        }
        if (eng2 < 1) {
            if (studentMajor.contains("아노덴")) {
                failure.append("교양필수 'Speaking EnglishⅡ' 미수강");
            } else {
                failure.append("교양필수 '영어Ⅱ' 미수강");
            }
            if (studentId >= 2019) {
                failure.append(" 또는 영어인증 미인증\n");
            } else {
                failure.append("\n");
            }
        }

        // 진로와상담 검사
        if (counseling < 4) failure.append("교양필수 '진로와상담' " + (4 - countCP) + "회 미수강\n");

        // 비교과 검사
        if (studentId >= 2020) {
            // 20학번 이후 마일리지 300점 이상
            if (mileage < 300) {
                failure.append("비교과 마일리지 " + (300 - mileage) + "점 미달\n");
            }
        } else if (studentId >= 2017) {
            // 17학번 이후 비교과 이수 학기 3학기 이상 또는 마일리지 300점 이상
            if (!(mileage >= 300 || nonSubject >= 3)) {
                failure.append("비교과 이수 학기 " + (3 - nonSubject) + "학기 미이수 또는 비교과 마일리지 " + (300 - mileage) + "점 미달\n");
            }
        }

        return failure;
    }

    /**
     * [핵심역량 검사](2020 학번 이상)
     */
    private static StringBuffer checkCoreKy(int studentId, List<String> selectKy) {
        StringBuffer failure = new StringBuffer();
        int humanities = 0; // '인문' 카운트
        int creativefusions= 0; // '창의융합' 카운트
        int globals = 0; // '글로벌' 카운트
        int readerships = 0; // '리더쉽' 카운트
        int communications = 0; // '소통' 카운트
        // 핵심역량 '인문' 과목 가져오기
        List<String> getHumanities = DBConnection.getHumanities();
        // 핵심역량 '창의융합' 과목 가져오기
        List<String> getCreativeFusions = DBConnection.getCreativeFusion();
        // 핵심역량 '글로벌' 과목 가져오기
        List<String> getGlobals = DBConnection.getGlobal();
        // 핵심역량 '리더쉽' 과목 가져오기
        List<String> getReaderships = DBConnection.getReadership();
        // 핵심역량 '소통' 과목 가져오기
        List<String> getCommunications = DBConnection.getCommunication();

        if (studentId >=2020) {
            for (int i = 0; i <selectKy.size(); i++) {
                String line = selectKy.get(i);
                if (getHumanities.contains(line)) humanities++;
                if (getCreativeFusions.contains(line)) creativefusions++;
                if (getGlobals.contains(line)) globals++;
                if (getReaderships.contains(line)) readerships++;
                if (getCommunications.contains(line)) communications++;

            }
            if (humanities < 1) failure.append("핵심역량 '인문' 미수강\n");
            if (creativefusions < 1) failure.append("핵심역량 '창의융합' 미수강\n");
            if (globals < 1) failure.append("핵심역량 '글로벌' 미수강\n");
            if (readerships < 1) failure.append("핵심역량 '리더쉽' 미수강\n");
            if (communications < 1) failure.append("핵심역량 '소통' 미수강\n");
        }
        return failure;
    }

    /**
     * [인재상 검사] (2019 학번만)
     */

    // 소통하는지성인 검사
    private static StringBuffer checkIntelligent(int studentId, List<String>allKy){
        StringBuffer failure = new StringBuffer();
        int intelligent = 0; //소통하는지성인 카운트
        int[] ky = new int[10];
        List<String> getIntelligent = DBConnection.getIntelligent(); //소통하는지성인 과목 가져오기
        if (studentId == 2019) {
            allKy.retainAll(getIntelligent);
            for(String str : allKy){
                int ky1 = DBConnection.getKyCredit(str);
                for (int i : ky) {
                    ky[i] = ky1;
                }
            if(IntStream.of(ky).sum() >= 5) intelligent++;
            }
        if (intelligent < 1) failure.append("소통하는지성인 미달성\n");
        }


        return failure;
    }

    // 실천하는 평화인 검사
    private static StringBuffer checkPeacemaker(int studentId, List<String>allKy){
        StringBuffer failure = new StringBuffer();
        int peacemaker = 0; //실천하는평화인 카운트
        int[] ky = new int[10];
        List<String> getPeacemaker = DBConnection.getPeacemaker(); //실천하는평화인 과목 가져오기
        if (studentId == 2019) {
            allKy.retainAll(getPeacemaker);
            for(String str : allKy){
                int ky1 = DBConnection.getKyCredit(str);
                for (int i : ky) {
                    ky[i] = ky1;
                }
            if(IntStream.of(ky).sum() >= 5) peacemaker++;
            }
        if ( peacemaker< 1) failure.append("실천하는평화인 미달성\n");
        }

        return failure;
    }

    // [도전하는 창의인] 검사
    private static StringBuffer checkCreator(int studentId, List<String>allKy){
        StringBuffer failure = new StringBuffer();
        int creator = 0; //소통하는지성인 카운트
        int[] ky = new int[10];
        List<String> getCreator = DBConnection.getCreator(); //소통하는지성인 과목 가져오기
        if (studentId == 2019) {
            allKy.retainAll(getCreator);
            for(String str : allKy){
                int ky1 = DBConnection.getKyCredit(str);
                for (int i : ky) {
                    ky[i] = ky1;
                }
            if(IntStream.of(ky).sum() >= 5) creator++;
            }
        if (creator < 1) failure.append("도전하는창의인 미달성\n");
        }


        return failure;
    }
}