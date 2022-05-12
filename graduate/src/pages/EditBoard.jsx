
import '../css/Board.css';
import '../css/Notice.css';

// 글쓰기 페이지 

function EditBoard(){
    return (
        <> 
            <main>
                <div className = "Board">
                    <div className = "BoardSize">
                        <div className = "Board__header">
                            <p>글쓰기</p>
                        </div>
                        <div className = "Board__navbar">
                            <div>전체 n건</div>
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
                            <div className = "Board__content--writer">배성규</div>
                            <div className = "Board__content--date">2022-12-31</div>
                            <div className = "Board__content--hits">18</div>
                        </div>
                       
                 
                    </div>
                </div>
            </main>
        </>
    );
}

export default EditBoard;