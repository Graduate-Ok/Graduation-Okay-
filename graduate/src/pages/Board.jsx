import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import '../css/Board.css';

import BoardRow from '../components/BoardRow';





const Board = () => {
    
    const[inputData, setInputData] = useState([]);

    useEffect(() =>{
        const fetchData = async() => {
            const response = await axios.get('http://localhost:8089/Board/');
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
                            <p className='minititle'>정보공유 게시판</p>
                        </div>
                        <div className="Board__navbar">
                            <div>전체 n건</div>
                            <div className="Board__search">
                                <select>
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                    <option value="writer">작성자</option>
                                </select>
                                <input type="text" placeholder="검색어를 입력하세요"></input>
                                <div className="Board__search--button">검 색</div>
                            </div>
                        </div>
                        <div className="Board__content--title">
                            <div className="Board__content--number">번호</div>
                            <div className="Board__content--name">제목</div>
                            <div className="Board__content--writer">작성자</div>
                            <div className="Board__content--date">날짜</div>
                            <div className="Board__content--hits">조회수</div>
                        
                        </div>
                        {
                                inputData.map((e)=>{
                                    return <BoardRow Board={e} />
                                })
                        }

                        
                        <div className="Board__footer">
                            <Link to="EditBoard" className="Board__footer--button">글쓰기</Link>

                        </div>
                        <div className="Board__page">
                            <div className="Board__page--button">이전</div>
                            <div className="Board__page--button">1</div>
                            <div className="Board__page--button">2</div>
                            <div className="Board__page--button">3</div>
                            <div className="Board__page--button">4</div>
                            <div className="Board__page--button">5</div>
                            <div className="Board__page--button">6</div>
                            <div className="Board__page--button">7</div>
                            <div className="Board__page--button">8</div>
                            <div className="Board__page--button">9</div>
                            <div className="Board__page--button">다음</div>
                        </div>
                    </div>
                </div>
            </main>
        </>
    );
}

export default Board;