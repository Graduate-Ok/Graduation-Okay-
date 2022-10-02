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


## 기능 💪🏽
<details>
    <summary>자세히</summary><br/><br/>

> 공지사항
- 공지/안내 조회 기능 
- 공지/안내 검색 기능 

> 졸업요건 확인 기능
- 졸업요건 검사

> 인기 교양 추천
- 인기 교양 과목 조회 기능 
- 교양 카운트 기능 

> 정보 공유 게시판
- 게시글 CRUD <br/>
- 게시글 조회 기능  <br/>
- 게시글 조회수 증가 기능 <br/> 
- 게시글 검색 기능 <br/>


</details>

## E-R 다이어그램📄
<img width = "100%" src = "https://user-images.githubusercontent.com/75983289/169945900-ab58d635-c4ec-48ff-8333-eaa132225d11.png"/>

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
