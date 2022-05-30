import {  Link } from 'react-router-dom';
import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';

// 글쓰기 페이지  
// localhost:3000/Board/EditBoard

function EditBoard() {
    const [editData, setEditData] = useState([]);

    useEffect(() =>{
        const fetchData = async() => {
            const response = await axios.post('http://localhost:8089/Board/EditBoard');
            setEditData(response.data);
        }
        fetchData();
    }, [])

    return (
        <>
            <main>
                <div className="Board">
                    <div className="BoardSize">
                        <div className="Board__header">
                            <p className='minititle'> 정보공유 게시판</p>
                        </div>

                        <form name="writing" method="post" action="/EditBoard">
                            <div className="Board__writecontainer">
                                <label for="edit" className='Board__writecontainer--head'> [ 정보 공유 게시판 ] </label>

                                <div className="Board__writecontainer--info">
                                    제목  <div className="Board__writecontainer--detail"> <input type="text" placeholder="제목을 입력하세요." name="title" className="Board__writecontainer--title" autoFocus required ></input></div>
                                    {/* id -> 작성자 ? */}
                                    <br/>ID <div className="Board__writecontainer--detail"> <input type="text" placeholder='아이디를 입력하세요.' name="id" className="Board__writecontainer--id" required></input></div>
                                    <br/> Password<div className="Board__writecontainer--detail">  <input type="password" placeholder='비밀번호를 입력하세요.' name="password" className="Board__writecontainer--password" required></input></div>
                                    <br/>
                                    내용 <div className="Board__writecontainer--detail"><textarea placeholder="내용을 입력하세요." name="content" className="Board__writecontainer--content" required></textarea></div>
                                </div>


                                
                            </div>


                            <div className="Board__footer">
                                <Link to="/" className="Board__footer--button">돌아가기</Link>
                                <button type="submit" className="Board__footer--button">등록하기</button>
                            </div>


                        </form>
                    </div>

                </div>
            </main>
        </>
    );
}

export default EditBoard;