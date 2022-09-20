import React from 'react';
import { Link, useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import useInput from '../hooks/useInput';



const EditBoardRow = ({ EditBoard }) => {
    const navigate = useNavigate();
    const handleSubmit = async (e) => {
        e.preventDefault();
        await axios.post('http://13.125.25.62:8089/Board/PostBoard', {
            brdTitle: title,
            brdWriter: writer,
            brdContent: content,
            brdPassword: password,
        });
        navigate('/Board');
    };

    // const handleSubmit = async (e) => {
    //     e.preventDefault();
    //     await axios.put('http://13.125.25.62:8089/Board/PostBoard', {
    //         brdTitle: title,
    //         brdWriter: writer,
    //         brdContent: content,
    //         brdPassword: password,
    //     });
    //     // navigate('/Board');
    // };


    const [title, onChangeTitle] = useInput('');
    const [writer, onChangeWriter] = useInput('');
    const [password, onChangePassword] = useInput('');
    const [content, onChangeContent] = useInput('');
    const [inputData, setInputData] = useState([]);

    let params = useParams().brdKey;
    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(
                `http://13.125.25.62:8089/Board/${params}`,
            );
            setInputData(response.data);
        };
        fetchData();
    }, []);


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
                        {' '}
                        [ 정보 공유 게시판 ]{' '}
                    </label>

                    <div className="Board__writecontainer--info">
                        제목{' '}
                        <div className="Board__writecontainer--detail">
                            {''}
                            <input
                                type="text"
                                onChange={onChangeTitle}
                                placeholder="제목을 입력하세요."
                                value={EditBoard.brdTitle}
                                name="brdTitle"
                                id="brdTitle"
                                className="Board__writecontainer--title"
                                autoFocus
                                required
                            ></input>
                        </div>
                        <br />
                        작성자{' '}
                        <div className="Board__writecontainer--detail">
                            {' '}
                            <input
                                type="text"
                                onChange={onChangeWriter}
                                placeholder="작성자의 이름을 입력하세요."
                                value={EditBoard.brdWriter}
                                name="brdWriter"
                                id="brdWriter"
                                className="Board__writecontainer--writer"
                                required
                            ></input>
                        </div>
                        {/* <br /> Password (수정/삭제시 비밀번호가
                                    필요합니다.)
                                    <div className="Board__writecontainer--detail">
                                        {' '}
                                        <input
                                            type="password"
                                            onChange={onChangePassword}
                                            placeholder="비밀번호를 입력하세요."
                                            name="brdPassword"
                                            id="brdPassword"
                                            className="Board__writecontainer--password"
                                            required
                                        ></input>
                                    </div> */}
                        <br />
                        내용{' '}
                        <div className="Board__writecontainer--detail">
                            <textarea
                                placeholder="내용을 입력하세요."
                                onChange={onChangeContent}
                                name="brdContent"
                                id="brdContent"
                                className="Board__writecontainer--content"
                                required
                            >{EditBoard.brdContent}</textarea>
                        </div>
                    </div>
                </div>

                <div className="Board__footer">

                    <button
                        onClick={handleSubmit}
                        type="submit"
                        id="submit"
                        name="submit"
                        className="Board__footer--button"
                    >
                        수정하기
                    </button>

                    
                    <div
                        onClick={() => navigate(`http://13.125.25.62:8089/Board`)}
                        className="Board__footer--button"
                    >
                        돌아가기
                    </div> 
                   

                </div>
            </form>
        </div>
    )


}

export default EditBoardRow;