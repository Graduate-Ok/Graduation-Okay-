import { Link, useParams, useNavigate } from 'react-router-dom';
import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import ViewBoardRow from '../components/ViewBoardRow';

/**
 *
 * @description ViewBoard 컴포넌트 페이지
 */
function ViewBoard() {
    let params = useParams().brdKey;
    const [inputData, setInputData] = useState([]);
    const navigate = useNavigate();

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

    const handleUpdate = async (e) => {
        e.preventDefault();

        if (
            window.confirm(
                `비밀번호가 일치해야 수정됩니다.
수정하시겠습니까?`,
            )
        ) {
            const response = await axios.get(
                `http://localhost:8089/Board/checkPw/${params}`,
            );
            // alert(response.data);
            // console.log(response.data);
            navigate(`../Board/EditBoard/${params}`);
        } else {
            alert('수정이 취소되었습니다');
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
                            <div
                                onClick={handleUpdate}
                                className="Board__update--button"
                            >
                                수정
                            </div>
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
