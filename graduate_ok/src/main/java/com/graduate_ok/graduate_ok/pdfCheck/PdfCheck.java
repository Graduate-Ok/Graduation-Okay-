package com.graduate_ok.graduate_ok.pdfCheck;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.*;

public class PdfCheck {
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
     * ㄴ19학번 교양 인재상별 1과목 이상
     * ㄴ20학번 이후 교양 핵심역량별 1과목 이상
     *
     * [부전공 검사] 부전공 21학점 이상 들었는지 검사
     *
     * [복수전공 검사] 주전공과 복수전공 각 36학점 이상 들었는지 검사
     *
     * ! 고려 안 한 대상 !
     * 1) 편입생
     */

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> a = execute("각자 PDF 파일 경로");

        // test 교양 카운트 초기화
        //DBConnection.settingKyCount0();
    }

    public static HashMap<String, Object> execute(String fileName) throws Exception {
        HashMap<String, Object> result = new HashMap<>();

        int studentId = 0; // 학번
        String studentMajor = ""; // 학과 (주전공)
        String studentSubMajor = ""; // 부전공
        String studentDoubleMajor = ""; // 복수전공

        int totalCredit = 0; // 총 취득학점
        int kyCredit = 0; // 교양 학점
        int majorCredit = 0; // 전공 학점
        int subMajorCredit = 0; // 부전공 학점
        int doubleMajorCredit = 0; // 복수전공 학점

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
        // 파일 읽어오기
        String[] list = PdfRead(fileName);

        // 한신대 학업성적확인서 확인
        if (checkPDF(list) == 0) throw new Exception();

        // test (pdf 전체 출력)
//        for (String str : list) {
//            System.out.println(str);
//        }


        /**
         * 기본 정보 추출
         */
        for (int i = 0; i < list.length; i++) {
            String line = list[i];

            // 학번 추출
            if (line.contains("학 번")) {
                studentId = Integer.parseInt(line.substring(4, 8));
            }

            // 학과 추출 (주전공)
            if (line.contains("부전공Ⅰ")) {
                String[] strings = list[i - 1].split(" ");
                int length = strings[2].length();// - 1;
                studentMajor = strings[2].substring(0, length);
            }

            // 학과 추출 (부전공)
            if (line.contains("부전공Ⅱ")){
                String[] strings = line.split(" ");
                if (!strings[0].contains("부전공Ⅱ")) {
                    studentSubMajor = strings[0];
                }
            }

            // 학과 추출 (복수전공)
            if (line.contains("복수전공Ⅰ")) {
                String[] strings = line.split(" ");
                if(!strings[7].contains("복수전공Ⅱ")) {
                    studentDoubleMajor = strings[7];
                }
            }


            // 총 취득학점 추출
            if (line.contains("총 취득학점")) {
                int length = line.length();// - 1;
                totalCredit = Integer.parseInt(line.substring(7, length).trim());
            }

            // 교양, 전공 이수학점 추출
            if (line.contains("교양: ") && line.contains("전공: ")) {
                kyCredit = Integer.parseInt(line.substring(4, 6).trim());
                majorCredit = Integer.parseInt(line.substring(11, 13).trim());
            }

            // 부전공 이수학점 추출
            if (line.contains("부전공:")) {
                subMajorCredit = Integer.parseInt(line.substring(5, 7).trim());
            }

            // 복수전공 이수학점 추출
            if (line.contains("복수:")) {
                doubleMajorCredit = Integer.parseInt(line.substring(4,6).trim());
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

            // 비교과 이수 학기 카운트
            if (line.contains("학기 인정")) {
                nonSubject++;
            }

            // 마일리지 추출
            if (line.contains("마일리지")) {
                int length = line.length();// - 1;
                mileage = Integer.parseInt(line.substring(22, length));
            }

            // 영어인증자 추출
            if (line.contains("영어인증")) {
                engCertification = true;
            }

            // 모든 교양 과목 추출 (for 인재상 & 핵심역량 검사, 교양 카운트 증가)
            if ((line.startsWith("교선") || line.startsWith("교필")) && !line.contains("NP")) {
                String[] strings = line.split("\\s+");
                if (strings.length < 5) {
                    allKy.add(strings[2]);
                } else if (!(strings[4].contains("F"))) {
                    allKy.add(strings[2]);
                }
            }
        }
        // test
        System.out.println("학번 : " + studentId);
        System.out.println("학과 : " + studentMajor);
        System.out.println("부전공 : " + studentSubMajor);
        System.out.println("복수전공 : " + studentDoubleMajor);
        System.out.println("총 취득학점 : " + totalCredit);
        System.out.println("교양학점 : " + kyCredit);
        System.out.println("전공학점 : " + majorCredit);
        System.out.println("부전공 학점 : " + subMajorCredit);
        System.out.println("복수전공 학점 : " + doubleMajorCredit);
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
        failure.append(checkRequiredMajor(studentId, studentMajor, requiredMajor));

        /**
         * [교양필수 검사]
         */
        failure.append(checkRequiredKy(studentId, studentMajor, nonSubject, mileage, engCertification, requiredKy));


        /**
         * [핵심역량 검사]
         */
        failure.append(checkCoreKy(studentId, allKy));

        /**
         * [인재상 검사]
         */
        failure.append(checkTalent(studentId, allKy));

        /**
         * 교양 카운트 증가
         */
        failure.append(updateKyCount(allKy));


        /**
         * [부전공 검사]
         */
        if (!studentSubMajor.equals("")) {
            failure.append(checkSubMajor(subMajorCredit));
        }


        /**
         *  [복수전공 검사]
         */
        if(!studentDoubleMajor.equals("")) {
            failure.append(checkDoubleMajor(majorCredit, doubleMajorCredit, studentId, requiredMajor, studentDoubleMajor, studentMajor));
        }


        // test
        System.out.println("\n===부족한 요건 출력===\n" + failure.toString());


        // 반환할 데이터 담기
        result.put("totalCredit", totalCredit); // 총 취득학점
        result.put("kyCredit", kyCredit); // 교양학점
        result.put("majorCredit", majorCredit); // 전공학점
        result.put("nonSubject", nonSubject); // 비교과 이수학기
        result.put("mileage", mileage); // 마일리지
        result.put("failure", failure); // 부족한 졸업요건
        if (failure.toString().equals("")) {
            result.put("result", 1); // 졸업가능
        } else {
            result.put("result", 0); // 졸업 불가능
        }

        return result;
    }


    /**
     * pdf 읽어오기
     */
    private static String[] PdfRead(String fileName) throws Exception {

        File source = new File(fileName);
        PDDocument pdfDoc = PDDocument.load(source);
        String text = new PDFTextStripper().getText(pdfDoc);
        String[] pdf = text.split("\n");

        return pdf;
    }


    /**
     * [파일 검사] 한신대학교 학업성적확인서 pdf 파일이 맞는지 검사
     */
    private static Integer checkPDF(String[] list) {
        String text = Arrays.toString(list);

        if (text.contains("포털>한신종합정보>성적")) {
            return 1;
        }

        return 0;
    }


    /**
     * [학점 검사]
     */
    private static StringBuffer checkCredit(int studentId, String studentMajor, int totalCredit, int kyCredit, int majorCredit) {
        StringBuffer failure = new StringBuffer();

        // 해당 학과 졸업학점 가져오기
        int graduateCredit = DBConnection.getGraduateCredit(studentMajor.substring(0, 3));
        // 해당 학과 전공최소학점 가져오기
        int majorMinCredit = DBConnection.getMajorMinCredit(studentMajor.substring(0, 3));

        // (총 취득학점 - 초과한 교양 학점)
        if (studentId <= 2016) {
            // 16학번 이전 교양 학점 : 35~45학점
            if (kyCredit > 45) {
                totalCredit -= (kyCredit - 45);
                failure.append("교양학점 " + (kyCredit - 45) + "학점 초과되어 총 취득학점에서 " + (kyCredit - 45) + "학점 제외 (총 취득학점 : " + totalCredit + "학점)\n");
            }
        } else {
            // 17학번 이후 교양 학점 : 35~49학점
            if (kyCredit > 49) {
                totalCredit -= (kyCredit - 49);
                failure.append("교양학점 " + (kyCredit - 49) + "학점 초과되어 총 취득학점에서 " + (kyCredit - 49) + "학점 제외 (총 취득학점 : " + totalCredit + "학점)\n");
            }
        }

        // 1. 졸업학점 검사
        if (graduateCredit > totalCredit) {
            failure.append("졸업학점 " + (graduateCredit - totalCredit) + "학점 미달\n");
        }

        // 2. 교양학점 검사
        if (kyCredit < 35) {
            failure.append("교양학점 " + (35 - kyCredit) + "학점 미달\n");
        }

        // 3. 전공 최소이수학점 검사
        if (majorMinCredit > majorCredit) {
            failure.append("전공학점 " + (majorMinCredit - majorCredit) + "학점 미달\n");
        }

        return failure;
    }


    /**
     * [전공필수 검사]
     */
    private static StringBuffer checkRequiredMajor(int studentId, String studentMajor, List<String> requiredMajor) {
        StringBuffer failure = new StringBuffer();

        // 해당 학과에서 전공필수 과목 가져오기
        List<String> requiredMajors = DBConnection.getRequiredMajor(studentId, studentMajor.substring(0, 3));

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

        for (String line : requiredKy) {
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
     * [핵심역량 검사] (2020 학번 이상)
     */
    private static StringBuffer checkCoreKy(int studentId, List<String> allKy) {
        StringBuffer failure = new StringBuffer();

        // 2020 미만 학번이면 검사 필요없이 비어 있는 스트링버퍼 return
        if (studentId < 2020) return failure;

        String[] coreType = {"인문", "소통", "지식정보", "창의융합", "리더십", "글로벌"};

        for (String type : coreType) {
            List<String> cores = new ArrayList<>();
            cores.addAll(DBConnection.getCore1(type));
            cores.addAll(DBConnection.getCore2(type));

            cores.retainAll(allKy);

            // test
            System.out.println("\n===핵심역량 [" + type + "] 과목===");
            for (String str : cores) {
                System.out.println(str);
            }

            if (cores.size() < 1) {
                failure.append("교양 핵심역량 '" + type + "' 미수강\n");
            }
        }

        return failure;
    }


    /**
     * [인재상 검사] (2019 학번만)
     */
    private static StringBuffer checkTalent(int studentId, List<String> allKy) {
        StringBuffer failure = new StringBuffer();

        // 2019 학번 아니면 검사 필요없이 비어 있는 스트링버퍼 return
        if (studentId != 2019) return failure;

        String[] talentType = {"소통하는지성인", "실천하는평화인", "도전하는창의인"};

        for (String type : talentType) {

            List<String> talents = new ArrayList<>();
            talents.addAll(DBConnection.getTalent1(type));
            talents.addAll(DBConnection.getTalent2(type));

            talents.retainAll(allKy);

            // test
            System.out.println("\n===인재상 [" + type + "] 과목===");
            for (String str : talents) {
                    System.out.println(str);
            }

            if (talents.size() < 1) {
                failure.append("교양 인재상 '" + type + "' 미수강\n");
            }

        }

        return failure;
    }

    /**
     * [부전공 검사]
     */
    private static StringBuffer checkSubMajor(int credit) {
        StringBuffer failure = new StringBuffer();

        if (credit < 21) {
            failure.append("부전공 " + (21 - credit) + "학점 미달\n");
        }

        return failure;
    }

    /**
     * [복수전공 검사]
     */
    private static StringBuffer checkDoubleMajor(int credit1, int credit2,int studentId, List<String> requiredMajor, String studentMajor1, String studentMajor2){
        StringBuffer failure = new StringBuffer();

        // 전공학점, 복수전공 학점 36점 이상 들었는지 검사
        if(credit1 < 36){
            failure.append("전공학점 " + (36-credit1) + " 미달\n");
        }
        if(credit2 < 36){
            failure.append("복수전공학점 " + (36-credit2) + " 미달\n");
        }

        // 전공필수 리스트 두개 가져와서 합침
        List<String> requiredMajor1 = DBConnection.getRequiredMajor(studentId, studentMajor1);
        List<String> requiredMajor2 = DBConnection.getRequiredMajor(studentId, studentMajor2);

        List<String> requiredDoubleMajors = new ArrayList<>();
        requiredDoubleMajors.addAll(requiredMajor1);
        requiredDoubleMajors.addAll(requiredMajor2);

        Collection<String> std = requiredMajor;
        requiredDoubleMajors.removeAll(std);

        for (String str : requiredDoubleMajors) {
            failure.append("전공필수 '" + str + "' 미수강\n");
        }

        return failure;
    }


    /**
     * 교양 카운트 증가
     */
    private static StringBuffer updateKyCount(List<String> allKy) {
        StringBuffer failure = new StringBuffer();

        for (String name : allKy) {
            if (DBConnection.updateKyCount(name) == 0) {
                System.out.println("** 교양 [" + name + "] 카운트 실패!\n");
            }
        }

        return failure;
    }
}