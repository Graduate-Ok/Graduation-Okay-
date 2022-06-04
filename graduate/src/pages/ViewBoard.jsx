import { Link, useParams} from 'react-router-dom';
import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
// 글보기 페이지  

import ViewBoardRow from "../components/ViewBoardRow"


function ViewBoard() {
    let params = useParams().brdKey;
    console.log(params);
    const [inputData, setInputData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(`http://localhost:8089/Board/${params}`);
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
                           
                            inputData.map((inputData) => {
                                console.log(inputData.brdKey);
                                return <ViewBoardRow ViewBoard={inputData} key = {inputData.brdKey}  />
                            })
                        }

                        <div className="Board__footer">
                            {/* 수정 부분으로 넘어가도록 */}
                            <Link to="/" className="Board__update--button">수정</Link> &nbsp;
                            <button className="Board__delete--button">삭제</button>
                        </div>

                    </div>

                </div>

            </main>
        </>
    );
}

export default ViewBoard;