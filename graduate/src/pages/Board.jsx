import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import '../css/Board.css';
import useInput from '../hooks/useInput';
import BoardRow from '../components/BoardRow';
import { navigate } from 'pos/lexicon';

const Board = () => {
    const [inputData, setInputData] = useState([]);
    const [srchType, setSrchType] = useState('');
    const [srchKeyword, setSrchKeyword] = useState('');
    const [page, setPage] = useState(1);

    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(
                `http://localhost:8089/Board/?srchType=${srchType}&srchKeyword=${srchKeyword}&page=${page}`,
            );
            //console.log(response.data);
            setInputData(response.data.boardDtoList);
        };
        fetchData();
    }, []);

    // 페이지 번호 받아오기
    const [totalPageCnt, setTotalPageCnt] = useState();

    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(`http://localhost:8089/Notice/`);
            setTotalPageCnt(response.data.searchHelper.totalPageCnt);
            for (let i = 1; i <= response.data.searchHelper.pageSize; i++) {
                if (i === totalPageCnt) {
                    break;
                } else {
                }
            }
        };
        fetchData();
    }, []);
    //console.log(page);

    // 검색 쿼리
    const handleSubmit = async (e) => {
        e.preventDefault();
        const select = document.forms['searchBar']['srchType'].value;
        const search = document.forms['searchBar']['srchKeyword'].value;
        if (search === '') {
            alert('검색어를 입력해주세요!');
            const response = await axios.get(`http://localhost:8089/Board/`);
            setInputData(response.data.boardDtoList);
        } else {
            const response = await axios.get(
                `http://localhost:8089/Board/?srchType=${select}&srchKeyword=${search}`,
            );
            setInputData(response.data.boardDtoList);
        }
    };

    const handleKeyUp = (e) => {
        if (e.key === 13) {
            handleSubmit();
        }
    };
    return (
        <>
            <main>
                <div className="Board">
                    <div className="BoardSize">
                        <div className="Board__header">
                            <p className="minititle">정보공유 게시판</p>
                        </div>
                        <div className="Board__navbar">
                            <div>전체 {inputData.length}건</div>
                            <form className="Board__search" name="searchBar">
                                <select name="srchType" id="srchType">
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                    <option value="writer">작성자</option>
                                </select>
                                <input
                                    type="text"
                                    placeholder="검색어를 입력하세요"
                                    name="srchKeyword"
                                    id="srchKeyword"
                                    onKeyUp={handleKeyUp}
                                ></input>
                                <div
                                    className="Board__search--button"
                                    id="submit"
                                    name="submit"
                                    onClick={handleSubmit}
                                >
                                    검 색
                                </div>
                            </form>
                        </div>
                        <div className="Board__content--title">
                            <div className="Board__content--number">번호</div>
                            <div className="Board__content--name">제목</div>
                            <div className="Board__content--writer">작성자</div>
                            <div className="Board__content--date">날짜</div>
                            <div className="Board__content--hits">조회수</div>
                        </div>
                        {inputData.map((inputData) => {
                            return (
                                <BoardRow
                                    Board={inputData}
                                    key={inputData.brdKey}
                                />
                            );
                        })}

                        <div className="Board__footer">
                            <Link
                                to="PostBoard"
                                className="Board__footer--button"
                            >
                                글쓰기
                            </Link>
                        </div>
                        <div className="Board__page">
                            <div className="Board__page--button">이전</div>
                            <div className="Board__page--button">
                                {
                                    // 페이지 데이터가 ex) 2 처럼 한개씩만 들어오는데
                                    // 그 전까지 페이징 하려면 어떻게 해야하지...
                                    totalPageCnt
                                }
                            </div>
                            <div className="Board__page--button">다음</div>
                        </div>
                    </div>
                </div>
            </main>
        </>
    );
};

export default Board;
