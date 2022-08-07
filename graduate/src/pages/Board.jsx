import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import '../css/Board.css';
import BoardRow from '../components/BoardRow';
import Pagination from '../components/Pagination';

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
            setInputData(response.data.boardDtoList);
        };
        fetchData();
    }, []);

    // 페이지네이션
    const [searchHelper, setSearchHelper] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(`http://localhost:8089/Board/`);
            setSearchHelper(response.data.searchHelper);
        };
        fetchData();
    }, []);

    const handlePagination = () => {
        // console.log('a');
    };

    const handlePageClick = async (i) => {
        const response = await axios.get(
            `http://localhost:8089/Board/?page=${i}`,
        );
        console.log(response.data.boardDtoList);
        setInputData(response.data.boardDtoList);
    };

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
                            <form
                                className="Board__search"
                                name="searchBar"
                                onSubmit={handleSubmit}
                            >
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
                                ></input>
                                <input
                                    type="submit"
                                    className="Board__search--button"
                                    id="submit"
                                    name="submit"
                                    value="검 색"
                                ></input>
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
                            <div
                                className="Board__page--button"
                                onClick={handlePagination}
                            >
                                이전
                            </div>

                            <Pagination
                                totalPageCnt={searchHelper.totalPageCnt}
                                pageSize={searchHelper.pageSize}
                                handlePageClick={handlePageClick}
                            />
                            <div
                                className="Board__page--button"
                                onClick={handlePagination}
                            >
                                다음
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </>
    );
};

export default Board;
