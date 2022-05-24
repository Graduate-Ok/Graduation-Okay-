import { Link } from 'react-router-dom';
import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
// 글보기 페이지  
// localhost:3000/ViewBoard
import ViewBoardRow from "../components/ViewBoardRow"


function ViewBoard() {

    const [inputData, setInputData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get('http://localhost:8080/Board/');
            setInputData(response.data);

        }
        fetchData();
    }, [])


    return (
        <>
            <main>
                <div className="Board">
                    <div className="BoardSize">
                        <div className="Board__header">
                            <Link to="/Board" className="Board__back--button">🔙 게시글 조회</Link>
                        </div>


                        {
                            inputData.map((e) => {
                                return <ViewBoardRow ViewBoard={e} />
                            })
                        }


                        {/* <div className="Board__info">
                            <span>작성자 :</span> <span className='BRD_WRITER'>김지윤 </span>
                            <span>&nbsp;작성일 :</span> <span className='BRD_WT_TIME'>2022.03.21 </span>
                            <span>&nbsp; 조회수 :</span> <span className='BRD_LOOKUP'> 300 </span>
                        </div>

                        <div className='Board__viewcontainer'>
                            <div id="BRD_title"> 제목 </div> <br />
                            <div id="BRD_content"> 내용 </div>
                        </div>  */}

                        <div className="Board__footer">
                            {/* 수정 부분으로 넘어가도록 */}
                            <Link to="/" className="Board__update--button">수정</Link> &nbsp;
                            <Link to="/Board" className="Board__delete--button">삭제</Link>
                        </div>

                    </div>

                </div>

            </main>
        </>
    );
}

export default ViewBoard;