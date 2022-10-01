import React from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import useInput from '../hooks/useInput';
import { API_URL, PORT_NUMBER } from '../utils/constant';

const EditBoardRow = ({ EditBoard }) => {
    const [title, onChangeTitle] = useInput('');
    const [writer, onChangeWriter] = useInput('');
    const [password, onChangePassword] = useInput('');
    const [content, onChangeContent] = useInput('');
    const [inputData, setInputData] = useState([]);
    const navigate = useNavigate();

    let params = useParams().brdKey;
    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(
                `${API_URL}${PORT_NUMBER}/Board/EditBoard/${params}`,
            );
            setInputData(response.data);
        };
        fetchData();
    }, []);

    /**
     * @description submit 이벤트
     */
    const handleSubmit = async (e) => {
        e.preventDefault();

        if (password === '') {
            alert('비밀번호를 입력해주세요.');
        }
        if (password !== '') {
            if (title === '' && writer === '' && content === '') {
                await axios.put(`${API_URL}${PORT_NUMBER}/Board/${params}`, {
                    brdKey: params,
                    brdTitle: EditBoard.brdTitle,
                    brdWriter: EditBoard.brdWriter,
                    brdContent: EditBoard.brdContent,
                    brdPassword: password,
                });
                alert('수정완료 되었습니다.');
                navigate('/Board');
            } else if (title === '' && writer === '') {
                await axios.put(`${API_URL}${PORT_NUMBER}/Board/${params}`, {
                    brdKey: params,
                    brdTitle: EditBoard.brdTitle,
                    brdWriter: EditBoard.brdWriter,
                    brdContent: content,
                    brdPassword: password,
                });
                alert('수정완료 되었습니다.');
                navigate('/Board');
            } else if (content === '' && writer === '') {
                await axios.put(`${API_URL}${PORT_NUMBER}/Board/${params}`, {
                    brdKey: params,
                    brdTitle: title,
                    brdWriter: EditBoard.brdWriter,
                    brdContent: EditBoard.brdContent,
                    brdPassword: password,
                });
                alert('수정완료 되었습니다.');
                navigate('/Board');
            } else if (content === '' && title === '') {
                await axios.put(`${API_URL}${PORT_NUMBER}/Board/${params}`, {
                    brdKey: params,
                    brdTitle: EditBoard.brdTitle,
                    brdWriter: writer,
                    brdContent: EditBoard.brdContent,
                    brdPassword: password,
                });
                alert('수정완료 되었습니다.');
                navigate('/Board');
            } else if (content === '') {
                await axios.put(`${API_URL}${PORT_NUMBER}/Board/${params}`, {
                    brdKey: params,
                    brdTitle: title,
                    brdWriter: writer,
                    brdContent: EditBoard.brdContent,
                    brdPassword: password,
                });
                alert('수정완료 되었습니다.');
                navigate('/Board');
            } else if (title === '') {
                await axios.put(`${API_URL}${PORT_NUMBER}/Board/${params}`, {
                    brdKey: params,
                    brdTitle: EditBoard.brdTitle,
                    brdWriter: writer,
                    brdContent: content,
                    brdPassword: password,
                });
                alert('수정완료 되었습니다.');
                navigate('/Board');
            } else if (writer === '') {
                await axios.put(`${API_URL}${PORT_NUMBER}/Board/${params}`, {
                    brdKey: params,
                    brdTitle: title,
                    brdWriter: EditBoard.brdWriter,
                    brdContent: content,
                    brdPassword: password,
                });
                alert('수정완료 되었습니다.');
                navigate('/Board');
            } else {
                await axios.put(`${API_URL}${PORT_NUMBER}/Board/${params}`, {
                    brdKey: params,
                    brdTitle: title,
                    brdWriter: writer,
                    brdContent: content,
                    brdPassword: password,
                });
                alert('수정완료 되었습니다.');
                navigate('/Board');
            }
        }
        return;
    };

    return (
        <div>
            <form
                name="writing"
                method="post"
                action="/Board/EditBoard/${params}"
            >
                <div className="Board__writecontainer">
                    <label
                        htmlFor="edit"
                        className="Board__writecontainer--head"
                    >
                        [ 정보 공유 게시판 ]
                    </label>

                    <div className="Board__writecontainer--info">
                        제목
                        <div className="Board__writecontainer--detail">
                            <textarea
                                type="text"
                                onChange={onChangeTitle}
                                placeholder={EditBoard.brdTitle}
                                name="brdTitle"
                                id="brdTitle"
                                className="Board__writecontainer--title"
                                autoFocus
                            ></textarea>
                        </div>
                        <br />
                        작성자
                        <div className="Board__writecontainer--detail">
                            <textarea
                                type="text"
                                onChange={onChangeWriter}
                                placeholder="작성자의 이름을 입력하세요."
                                name="brdWriter"
                                id="brdWriter"
                                className="Board__writecontainer--writer"
                            >
                                {EditBoard.brdWriter}
                            </textarea>
                        </div>
                        <br /> Password (수정/삭제시 비밀번호가 필요합니다.)
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
                            >
                                {EditBoard.brdContent}
                            </textarea>
                        </div>
                    </div>
                </div>

                <div className="Board__footer">
                    <div
                        onClick={handleSubmit}
                        type="submit"
                        id="submit"
                        name="submit"
                        className="Board__footer--button"
                    >
                        수정하기
                    </div>

                    <div
                        onClick={() => navigate(`../Board`)}
                        className="Board__footer--button"
                    >
                        돌아가기
                    </div>
                </div>
            </form>
        </div>
    );
};

export default EditBoardRow;
