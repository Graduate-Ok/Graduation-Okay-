import { Link } from 'react-router-dom';
import React from 'react';
// 글보기 페이지  
// localhost:3000/ViewBoard

function ViewBoard() {
    return (
        <>
            <main>
                <div className="Board">
                    <div className="BoardSize">
                        <div className="Board__header">
                            <Link to="/Board" className="Board__back--button">🔙 게시글 조회</Link>
                        </div>

                        <div className="Board__info">
                            <span>작성자 :</span> <span className='BRD_WRITER'>김지윤 </span>
                            <span>&nbsp;작성일 :</span> <span className='BRD_WT_TIME'>2022.03.21 </span>
                            <span>&nbsp; 조회수 :</span> <span className='BRD_LOOKUP'> 300 </span>
                        </div>



                        <div className='view--container'>
                            <div classname="Board__content--BRD_TITLE"> 제목 </div>  <br />
                            <div classname="Board__content--BRD_CONTENT"> 내용은 여기에 </div>
                        </div>



                        <div className="Board__footer">
                            <Link to="/" className="Board__update--button">수정</Link>
                            <Link to="/Board" className="Board__delete--button">삭제</Link>
                        </div>

                    </div>

                </div>

            </main>
        </>
    );
}

export default ViewBoard;