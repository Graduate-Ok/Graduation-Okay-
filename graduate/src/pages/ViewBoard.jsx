import { Link, useParams, useNavigate } from 'react-router-dom';
import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
// 글보기 페이지

import ViewBoardRow from '../components/ViewBoardRow';

function ViewBoard() {
    let params = useParams().brdKey;

    const [inputData, setInputData] = useState([]);
    const navigate = useNavigate(); // 글 삭제 후 목록으로

    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(
                `http://localhost:8089/Board/${params}`,
            );
            setInputData(response.data);
        };
        fetchData();
    }, []);

    const handleDelete = async (e) => {
        e.preventDefault();
        const inputPassword = prompt(
            '작성 시 사용했던 비밀번호를 입력해주세요',
        );
        if (window.confirm('정말 삭제하시겠습니까?')) {
            const response = await axios.delete(
                `http://localhost:8089/Board/${params}?password=${inputPassword}`,
                {
                    brdPassword: inputPassword,
                },
            );
            alert(response.data);
            navigate('/Board');
        } else {
            alert('삭제가 취소되었습니다');
            navigate(0);
        }
    };
    return (
        <>
            <main>
                <div className="Board">
                    <div className="BoardSize">
                        <div className="Board__header">
                            <Link to="/Board" className="Board__back--button">
                                🔙 게시글 조회
                            </Link>
                        </div>

                        {inputData.map((inputData) => {
                            return (
                                <ViewBoardRow
                                    ViewBoard={inputData}
                                    key={inputData.brdKey}
                                />
                            );
                        })}

                        <div className="Board__footer">
                            {/* 수정 부분으로 넘어가도록 */}
                            <Link
                                to="../Board/EditBoard/{params}"
                                className="Board__update--button"
                            >
                                수정
                            </Link>{' '}
                            &nbsp;
                            <div
                                type="submit"
                                onClick={handleDelete}
                                className="Board__delete--button"
                            >
                                삭제
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </>
    );
}

export default ViewBoard;
