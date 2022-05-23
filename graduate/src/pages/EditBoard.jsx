import { useNavigate, Link } from 'react-router-dom';
import React from 'react';
import {useEffect,useState} from 'react';
import axios from 'axios';

// 글쓰기 페이지  
// localhost:3000/Board/EditBoard

function EditBoard(){
    let history = useNavigate();

    return (
        <> 
            <main>
                <div className = "Board">
                    <div className = "BoardSize">
                        <div className = "Board__header">
                            <p> 정보공유 게시판</p>
                        </div>

                        <form name = "writing" method = "post" action = "/Board">
                            <div className = "Board__writecontainer">
                                <label for = "edit" className='Board__writecontainer--head'>정보공유 게시판</label>
                                <input type = "text"  placeholder= "제목" name = "title" className = "Board__writecontainer--title" autoFocus required ></input>
                                <div className = "Board__writecontainer--info">
                                    <input type = "text" placeholder='ID' name = "id" required></input>
                                    <input type = "password" placeholder='PASSWORD' name = "password" required></input>
                                </div>
                                <textarea placeholder="내용을 입력하세요" name = "content" className='Board__writecontainer--content' required></textarea>
                            </div>
                            <div className = "Board__footer">
                                <Link to = "/" className = "Board__footer--button">돌아가기</Link>
                                <button type = "submit" className = "Board__footer--button">등록하기</button> 
                            </div>
                        </form>
                    </div>
                  
                </div>
            </main>
        </>
    );
}

export default EditBoard;