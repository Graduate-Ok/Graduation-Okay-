import '../css/ContentsPage.css';
import '../css/Notice.css'
import {useEffect, useState} from 'react';
import axios from 'axios';

import NoticeRow from "../components/NoticeRow"
import Pagination from '../components/Pagination';


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

    const [posts, setPosts] = useState([]); // posts state에는 실제 json 데이터들이 들어옴
    const [loading, setLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [postsPerPage, setPostsPerPage] = useState(10); 

    const [dataIndex, setDataIndex] = useState(0);
    const [srchType, setSrchType] = useState("");
    const [srchKeyword, setSrchKeyword] = useState("");
    const [page, setPage] = useState(1);
    
    useEffect(() =>{
        const fetchData = async() => { 
            setLoading(true);
            // const response = await axios.get(`http://localhost:8089/Notice/list/${dataIndex}`);
            const response = await axios.get(`http://localhost:8089/Notice/list?srchType=${srchType}&srchKeyword=${srchKeyword}&page=${page}`);
            setInputData(response.data.noticeDtoList);
            setPosts(response.data.noticeDtoList);
            setLoading(false);
        }
        fetchData();  
    }, [dataIndex]); // 컴포넌트가 맨 처음 렌더링(마운트)될 때만 정의한 함수 실행, 업데이트 될때는 실행 x 

    console.log(posts); 

    const indexOfLast = currentPage * postsPerPage;
    const indexOfFirst = indexOfLast - postsPerPage;
    function currentPosts(tmp) {
        let currentPosts = 0;
        currentPosts = tmp.slice(indexOfFirst, indexOfLast);
        return currentPosts;
    }
    return (
        <>

            <main>
                <div className="Notice">
                    <div className="NoticeSize">
                        <div className="Notice__header">
                            <p className='minititle'>공지사항</p>
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
                            
                            <Pagination postsPerPage = {postsPerPage} totalPosts = {posts.length} paginate = {setCurrentPage} />
                        </div>
                    </div>

                </div>
            </main>
        </>
    );
}

export default Notice;