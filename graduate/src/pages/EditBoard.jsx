import { useNavigate, useParams } from 'react-router-dom';
import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import useInput from '../hooks/useInput';
import EditBoardRow from '../components/EditBoardRow';
/**
 *
 * @description 글 수정 페이지 컴포넌트
 */
const EditBoard = ({ brdKey }) => {
    const navigate = useNavigate();

    const [title, onChangeTitle] = useInput('');
    const [writer, onChangeWriter] = useInput('');
    const [password, onChangePassword] = useInput('');
    const [content, onChangeContent] = useInput('');
    const [inputData, setInputData] = useState([]);

    /**
     *
     * @description 클릭이벤트 시 글 수정
     */
    const handleSubmit = async (e) => {
        e.preventDefault();
        await axios.post('http://localhost:8089/Board/PostBoard', {
            brdTitle: title,
            brdWriter: writer,
            brdContent: content,
            brdPassword: password,
        });
        navigate('/Board');
    };

    let params = useParams().brdKey;
    let key = useParams().brdKey;
    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(
                `http://localhost:8089/Board/${params}`,
            );
            setInputData(response.data);
        };
        fetchData();
    }, []);

    return (
        <>
            <main>
                <div className="Board">
                    <div className="BoardSize">
                        <div className="Board__header">
                            <p className="minititle"> 정보공유 게시판</p>
                        </div>

                        {inputData.map((inputData) => {
                            return <EditBoardRow EditBoard={inputData} />;
                        })}

                        {/* <form
                            name="writing"
                            method="post"
                            action="/Board/EditBoard"
                        >
                            <div className="Board__writecontainer">
                                <label
                                    for="edit"
                                    className="Board__writecontainer--head"
                                >
                                    [ 정보 공유 게시판 ]
                                </label>

                                <div className="Board__writecontainer--info">
                                    제목
                                    <div className="Board__writecontainer--detail">
                                        <input
                                            type="text"
                                            onChange={onChangeTitle}
                                            placeholder={inputData.brdTitle}
                                            name="brdTitle"
                                            id="brdTitle"
                                            className="Board__writecontainer--title"
                                            autoFocus
                                            required
                                        ></input>
                                    </div>
                                    <br />
                                    작성자
                                    <div className="Board__writecontainer--detail">
                                        <input
                                            type="text"
                                            onChange={onChangeWriter}
                                            placeholder="작성자의 이름을 입력하세요."
                                            name="brdWriter"
                                            id="brdWriter"
                                            className="Board__writecontainer--writer"
                                            required
                                        ></input>
                                    </div>
                                    <br /> Password (수정/삭제시 비밀번호가
                                    필요합니다.)
                                    <div className="Board__writecontainer--detail">
                                        <input
                                            type="password"
                                            onChange={onChangePassword}
                                            placeholder="비밀번호를 입력하세요."
                                            name="brdPassword"
                                            id="brdPassword"
                                            className="Board__writecontainer--password"
                                            required
                                        ></input>
                                    </div>
                                    <br />
                                    내용
                                    <div className="Board__writecontainer--detail">
                                        <textarea
                                            placeholder="내용을 입력하세요."
                                            onChange={onChangeContent}
                                            name="brdContent"
                                            id="brdContent"
                                            className="Board__writecontainer--content"
                                            required
                                        ></textarea>
                                    </div>
                                </div>
                            </div>
                            <div className="Board__footer">
                                <div
                                    onClick={() => navigate(-1)}
                                    className="Board__footer--button"
                                >
                                    돌아가기
                                </div>
                                <button
                                    onClick={handleSubmit}
                                    type="submit"
                                    id="submit"
                                    name="submit"
                                    className="Board__footer--button"
                                >
                                    등록하기
                                </button>
                            </div>
                        </form> */}
                    </div>
                </div>
            </main>
        </>
    );
};

export default EditBoard;
