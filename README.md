# <img width = "100%" src = "https://user-images.githubusercontent.com/75983289/164976416-004d3626-3a5c-4eb5-95d6-e94d1833a392.png"/>
한신대학교 재학생 누구나 본인의 졸업요건을 조회할 수 있는 웹사이트 
| [홈페이지 바로가기](http://www.hs-graduate-ok.com/) | [노션 바로가기](https://cyclic-pleasure-1d8.notion.site/cfc00b3dc86c48ffb1f7b8daa0fb2cc4?v=37062694a379480c8984998f79448e3c) |
|:---:|:---:|

## TEAM MEMBER 👨‍👨‍👧‍👧
| 팀원 | 역할 | Github | 
|:---:|:---:|:---:| 
| 배성규 | Front-end | https://github.com/pangkyu | 
| 박수빈 | Back-end | https://github.com/psb0320 |
| 김지윤 | Front-end | https://github.com/JiYoon0712 |
| 김민석 | Back-end | https://github.com/alstjr |

## 프로젝트 개요 👀
#### 프로젝트 선정 이유
1. 기존 한신대학교 졸업사정 셀프 테스트는 졸업 예정자인 학우들만 결과를 확인할 수 있음
2. 학과 사무실에 셀프 테스트 양식을 작성하여 직접 제출해야함
3. 학과/학번별로 졸업요건이 상이하기 때문에 졸업요건을 정확하게 파악하기 어려움
4. 졸업사정 셀프 테스트 결과를 조교가 직접 체크해야 하기 때문에 결과지를 받는 데 많은 시간이 소요됨
#### 프로젝트 목적 및 기대효과
1. 학우들이 웹 사이트에서 학업성적확인서 PDF만 올려서 빠르고 간편하게 본인의 졸업요건을 파악하고 부족한 부분을 알 수 있게 도와줌
2. 조교들은 졸업 요건을 확인하는 업무를 하지 않아도 되기 때문에 업무 부담이 줄어서 다른 업무에 보다 더 집중할 수 있음
3. 타 대학도 기존 우리 학교와 졸업 요건 확인을 수기로 하기 때문에 이 프로젝트를 통해 정확한 결과 도출 및 시간 절약을 할 수 있는 기대효과
## 사용 스택 🛠️
 <div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <br>
  
  <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> 
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <br>
   
  <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">
  <img src="https://img.shields.io/badge/amazonaws-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white"> 
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <br>
</div>


## 기능 & UI 💪🏽
<details>
    <summary>자세히</summary><br/><br/>
    
> 메인페이지
<details>
    <summary>메인화면 UI</summary>
    
![화면 캡처 2022-10-02 215117](https://user-images.githubusercontent.com/75983289/193454888-82442ef2-3945-4301-96b9-7fbbfe288f6c.png)
- '졸업가능?' 웹사이트의 로고와 함께 각 주요 기능으로 넘어갈 수 있는 네비게이션 바, 그리고 학교 이미지 영역과 졸업요건과 관련된 사이트 연결이 가능한 푸터바로 구성 
 </details><br/>

> 공지사항
- 공지사항 게시판에서는 졸업요건이나 공지 또는 안내사항을 확인할 수 있습니다. 
 <details>
    <summary>글 목록 조회기능</summary>
    
![image](https://user-images.githubusercontent.com/75983289/193455218-76afd6ef-30e6-4bd8-b070-f6b89cdec944.png)
- 우측 상단에 있는 검색창을 통해 제목/내용으로 검색이 가능합니다. 왼쪽 상단에 있는 공지/안내 탭을 통해 카테고리별로 공지 글을 확인할 수 있습니다.

 </details><br/>
 <details>
    <summary>공지사항 세부내용 확인</summary>
    
![image](https://user-images.githubusercontent.com/75983289/193455381-615a9335-c7f0-4b7f-bfcc-f8f19b909e1e.png)
- 페이지 이동없이 게시글 제목을 누르면 내용을 확인할 수 있습니다.     
 </details><br/>

> 졸업요건 확인 기능
  <details>
     <summary>졸업요건 검사</summary>
     
![image](https://user-images.githubusercontent.com/75983289/193455482-f146c715-e769-4a85-baef-40cdb01e3451.png)
- '학업성적 확인서 PDF 업로드 버튼'을 통해 사용자의 학업성적 확인서 PDF를 업로드할 수 있습니다. 업로드 버튼 아래에는 학업성적확인서 PDF를 다운로드할 수 있는 경로를 제공합니다. 파일을 업로드하면 하단에 졸업가능 여부와 기본정보가 출력되며, 졸업이 불가능한 경우에는 부족한 졸업요건도 추가로 출력됩니다.   

 < 졸업요건이 충족된 경우 >
        
![image](https://user-images.githubusercontent.com/75983289/193455611-7afa08c9-17bf-41b2-88b2-7bc060675caf.png)
    
   
 < 졸업요건이 충족되지 않은 경우 > 
 
![image](https://user-images.githubusercontent.com/75983289/193455711-ff502706-0651-46ef-ad43-ed28b9f1ab7b.png)
 
 < 한신대학교 학업성적확인서 pdf파일이 아닌 경우> 

![image](https://user-images.githubusercontent.com/75983289/193455770-cd23606f-935c-4a90-838c-1980d6e3aec6.png)
![image](https://user-images.githubusercontent.com/75983289/193455773-7d9abf19-cc15-4fe7-b21c-fa329744fcd8.png)
 
  </details><br/>

> 인기 교양 추천
  <details>
     <summary>인기 교양 추천 페이지</summary>
     
![image](https://user-images.githubusercontent.com/75983289/193455824-0721f71f-aba2-4d03-a9e1-0076047266fa.png)
- 인기 교양 추천 페이지에서는 한신대하교 학생들이 수강한 인기 교양을 많이 수강한 순서대로 확인할 수 있습니다. 순위와 함께 수강 횟수, 해당 교양의 학점, 인재상, 핵심역량 과목을 확인할 수 있습니다. 또한 에브리타임 별점 순 상위 3개의 교양 과목도 확인할 수 있습니다.      
  </details><br/>

> 정보 공유 게시판

  <details>
    <summary>게시글 CRUD 기능</summary>
    
![image](https://user-images.githubusercontent.com/75983289/193455910-f017238a-6cdb-44a8-92b1-58cb8ea7d18e.png)
- 정보 공유 게시판에서는 유저들이 직접 졸업요건 또는 학사와 관련된 정보글을 올릴 수 있습니다. 우측 상단에 있는 검색창을 통해 제목/내용/글작성자 조건으로 검색을 할 수 있습니다. 또한 우측 하단 글쓰기 버튼을 통해 글쓰기 페이지로 이동하여 글을 작성할 수 있습니다. 게시글 목록에서 제목을 클릭하면 해당 게시글 페이지로 이동합니다.     

![image](https://user-images.githubusercontent.com/75983289/193456024-37bb6e36-bc6d-4277-b6ab-78a14591caaf.png)
- 게시글 페이지 우측 하단에 있는 수정&삭제 버튼을 통해 게시글을 수정 및 삭제를 할 수 있습니다. 수정&삭제시에는 비밀번호를 체크하여 일치 시 기능이 수행하게 됩니다. 

![image](https://user-images.githubusercontent.com/75983289/193456064-1f5d696e-2850-44bb-8d23-603a4ff91fe2.png)

  </details><br/>
 
> 피드백 게시판 
   <details>
    <summary>피드백 게시판</summary> 
  - 사용자가 졸업가능 서비스를 이용하며 느끼는 불편함 또는 건의사항을 개발자들에게 전달할 수 있는 페이지입니다. 사용자의 이메일 주소와 피드백을 원하는 내용을 보내면 팀 메일로 해당 내용이 전송됩니다. 
  
![image](https://user-images.githubusercontent.com/75983289/193456222-e10294b0-e0de-4dcf-946d-b35967d7ded8.png)
  
  </details><br/>

  
</details>



## E-R 다이어그램 & 유즈케이스📄
<img width = "100%" src = "https://user-images.githubusercontent.com/75983289/169945900-ab58d635-c4ec-48ff-8333-eaa132225d11.png"/>
<img width = "50%"  src = "https://user-images.githubusercontent.com/75983289/199025307-fe5eca66-ce06-4705-9c01-b5d758adb96f.png"/>

## Dependencies🖥️
 
```js
// Front-end 
"@testing-library/jest-dom": "^5.16.2",
"@testing-library/react": "^12.1.4",
"@testing-library/user-event": "^13.5.0",
"axios": "^0.26.1",
"http-proxy-middleware": "^2.0.6",
"react": "^17.0.2",
"react-dom": "^17.0.2",
"react-router-dom": "^6.3.0",
"react-scripts": "5.0.0",
"styled-components": "^5.3.5",
"styled-reset": "^4.3.4",
"web-vitals": "^2.1.4"
        
// Back-end
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-mustache'
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2'
compileOnly 'org.projectlombok:lombok'
developmentOnly 'org.springframework.boot:spring-boot-devtools'
runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
annotationProcessor 'org.projectlombok:lombok'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
implementation group: 'org.apache.pdfbox', name: 'pdfbox', version: '2.0.9'
implementation 'io.springfox:springfox-boot-starter:3.0.0' // swagger (api 명세서)
implementation 'org.apache.tika:tika-core:2.4.0' // 파일 확장자 검사 (MIME-Type 검사)
implementation 'org.apache.tika:tika-parsers:2.4.0' // 파일 확장자 검사 (MIME-Type 검사)
```
