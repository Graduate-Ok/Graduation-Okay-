import '../css/ContentsPage.css';
import '../css/Notice.css'
import {useEffect, useState} from 'react';
import axios from 'axios';

import NoticeRow from "../components/NoticeRow"


// 공지사항
/* 
  추가할 사항들 
  공지내용을 올림. 

  > 누르면 내용 쫙 펼쳐지고 닫아지게
  https://moonhouse.co.kr/html/529065

  접기 펼치기
  https://pjw48.net/wordpress/2017/04/02/html-details-tag/
  

  TAB
  https://carina16.tistory.com/173
*/



const Notice = () => {

   

    const[inputData, setInputData] = useState([]);

    useEffect(() =>{
        const fetchData = async() => {
            const response = await axios.get('http://localhost:8089/Notice/list');
            setInputData(response.data);

        }
        fetchData();  
    }, [])

    return (
        <>

            <main>
                <div className="Notice">
                    <div className="NoticeSize">
                        <div className="Notice__header">
                            <p>공지사항</p>
                        </div>

                        

                        <div className="Notice__navbar">
                            <div></div>

                        </div>

                        {/*TAB*/}
                        <div class="tab">
                            <ul class="tabnav">
                                <li><a href="#tab01"> 공지 </a></li>
                                <li><a href="#tab02"> 안내 </a></li>
                            </ul>
                            <div className="Notice__search">
                                    <select>
                                        <option value="title">제목</option>
                                        <option value="content">내용</option>
                                    </select>
                                    <input type="text" placeholder="검색어를 입력하세요"></input>
                                    <div className="Notice__search--button">검 색</div>
                                </div>

                            <div class="tabcontent">
                                {/*tab 1*/}
                                <div id="tab01">
                                    <div className="Notice__content--title">
                                        <div className="Notice__content--number">번호</div>
                                        <div className="Notice__content--category">카테고리</div>
                                        <div className="Notice__content--name">제목</div>
                                        <div className="Notice__content--date">날짜</div>
                                    </div>


                                    {
                                        inputData.map((e)=>{
                                            return <NoticeRow Notice={e} />
                                        })
                                    }




                                </div>
                            </div>
                            <div id="tab02">{/*tab 2 내용*/}</div>
                            
                            <div className="Notice__page" id="page-button">
                                <div className="Notice__page--button">이전</div>
                                <div className="Notice__page--button">1</div>
                                <div className="Notice__page--button">2</div>
                                <div className="Notice__page--button">3</div>
                                <div className="Notice__page--button">4</div>
                                <div className="Notice__page--button">5</div>
                                <div className="Notice__page--button">6</div>
                                <div className="Notice__page--button">7</div>
                                <div className="Notice__page--button">8</div>
                                <div className="Notice__page--button">9</div>
                                <div className="Notice__page--button">다음</div>
                            </div>
                        </div>
                    </div>

                </div>
            </main>
        </>
    );
}

export default Notice;