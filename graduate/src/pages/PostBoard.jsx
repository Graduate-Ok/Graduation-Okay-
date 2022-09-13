import { useNavigate } from 'react-router-dom';
import React from 'react';
import axios from 'axios';
import useInput from '../hooks/useInput';

/**
 *
 * @description PostBoard 컴포넌트 페이지
 */
const PostBoard = () => {
    const navigate = useNavigate();

    /**
     *
     * @description 버튼 클릭 시 직전 페이지로 돌아가는 기능
     */
    const handleSubmit = async (e) => {
        e.preventDefault();
        const title = document.forms['writing']['brdTitle'].value;
        const writer = document.forms['writing']['brdWriter'].value;
        const password = document.forms['writing']['brdPassword'].value;
        const content = document.forms['writing']['brdContent'].value;
        if (
            title === '' ||
            writer === '' ||
            password === '' ||
            content === ''
        ) {
            alert('비어있는 항목이 있습니다. 입력 후 다시 등록해주세요');
            return false;
        }
        await axios.post('http://13.125.25.62:8089/Board/PostBoard', {
            brdTitle: title,
            brdWriter: writer,
            brdContent: content,
            brdPassword: password,
        });
        navigate('/Board');
    };

    const [title, onChangeTitle] = useInput('');
    const [writer, onChangeWriter] = useInput('');
    const [password, onChangePassword] = useInput('');
    const [content, onChangeContent] = useInput('');

    return (
        <>
            <main>
                <div className="Board">
                    <div className="BoardSize">
                        <div className="Board__header">
                            <p className="minititle"> 정보공유 게시판</p>
                        </div>
                        <form
                            name="writing"
                            method="post"
                            action="/Board/PostBoard"
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
                                            placeholder="제목을 입력하세요."
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
                                <div
                                    onClick={handleSubmit}
                                    type="submit"
                                    id="submit"
                                    name="submit"
                                    className="Board__footer--button"
                                >
                                    등록하기
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </>
    );
};

export default PostBoard;
