import { Link} from 'react-router-dom';
import React from 'react';
import {useEffect,useState} from 'react';
import axios from 'axios';

// 글쓰기 페이지  
// localhost:3000/EditBoard

function EditBoard(){

    

    return (
        <> 
            <main>
                <div className = "Board">
                    <div className = "BoardSize">
                        <div className = "Board__header">
                            <p>정보공유 게시판</p>
                        </div>

                        <form name = "writing" method = "post" >
                            <div className = "Board__writecontainer">
                                <label for = "edit" >정보공유 게시판</label>
                                <input type = "text" className = "Board__write--title" placeholder= "제목" autoFocus required ></input>
                                <input type = "text" className = "Board__write--content" placeholder="내용을 입력하세요" required></input>

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