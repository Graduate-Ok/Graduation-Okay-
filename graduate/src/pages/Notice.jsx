import react from 'react';
import '../css/ContentsPage.css';
import '../css/Board.css';

// 공지사항
/* 
  추가할 사항들 
  공지내용을 올림. 
*/
function Notice() {
    return (
        <> 
            <main>
                <div className = "Board">
                    <div className = "BoardSize">
                        <div className = "Board__header">
                            <p>공지사항</p>
                        </div>
                        <div className = "Board__navbar">
                            <div></div>
                            <div className = "Board__search">
                                <select>
                                    <option value= "title">제목</option>
                                    <option value = "content">내용</option>
                                    <option value = "writer">작성자</option>
                                </select>
                                <input type = "text" placeholder= "검색어를 입력하세요"></input>
                                <div className= "Board__search--button">검 색</div>
                            </div>
                        </div>
                        <div className = "Board__content--title">
                            <div className = "Board__content--number">번호</div>
                            <div className = "Board__content--name">제목</div>
                            <div className = "Board__content--writer">작성자</div>
                            <div className = "Board__content--date">날짜</div>
                            <div className = "Board__content--hits">조회수</div>
                        </div>
                        <div className = "Board__content--content">
                            <div className = "Board__content--number">3</div>
                            <div className = "Board__content--name">세번째 글입니다</div>
                            <div className = "Board__content--writer">관리자A</div>
                            <div className = "Board__content--date">2022-11-31</div>
                            <div className = "Board__content--hits">1</div>
                        </div>
                        <div className = "Board__content--content">
                            <div className = "Board__content--number">2</div>
                            <div className = "Board__content--name">두번째 글입니다</div>
                            <div className = "Board__content--writer">관리자B</div>
                            <div className = "Board__content--date">2022-11-30</div>
                            <div className = "Board__content--hits">11</div>
                        </div>
                        <div className = "Board__content--content">
                            <div className = "Board__content--number">1</div>
                            <div className = "Board__content--name">첫번째 글입니다</div>
                            <div className = "Board__content--writer">관리자A</div>
                            <div className = "Board__content--date">2022-11-11</div>
                            <div className = "Board__content--hits">7</div>
                        </div>
                        <div className= "Board__page" id = "page-button">
                            <div className = "Board__page--button">이전</div>
                            <div className = "Board__page--button">1</div>
                            <div className = "Board__page--button">2</div>
                            <div className = "Board__page--button">3</div>
                            <div className = "Board__page--button">4</div>
                            <div className = "Board__page--button">5</div>
                            <div className = "Board__page--button">6</div>
                            <div className = "Board__page--button">7</div>
                            <div className = "Board__page--button">8</div>
                            <div className = "Board__page--button">9</div>
                            <div className = "Board__page--button">다음</div>
                        </div>      
                    </div>
                </div>
            </main>
        </>
    );
}

export default Notice;