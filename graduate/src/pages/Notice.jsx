import '../css/ContentsPage.css';
import '../css/Notice.css'


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



function Notice() {

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
                            <div className="Notice__search">
                                <select>
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                </select>
                                <input type="text" placeholder="검색어를 입력하세요"></input>
                                <div className="Notice__search--button">검 색</div>
                            </div>
                        </div>

                        {/*TAB*/}
                        <div class="tab">
                            <ul class="tabnav">
                                <li><a href="#tab01"> 공지 </a></li>
                                <li><a href="#tab02"> 안내 </a></li>
                            </ul>

                            <div class="tabcontent">
                                {/*tab 1*/}
                                <div id="tab01">
                                    <div className="Notice__content--title">
                                        <div className="Notice__content--number">번호</div>
                                        <div className="Notice__content--category">카테고리</div>
                                        <div className="Notice__content--name">제목</div>
                                        <div className="Notice__content--date">날짜</div>
                                    </div>


                                    <div className="Notice__content--content">
                                        <div className="Notice__content--number">3</div>
                                        <div className="Notice__content--category">공지</div>
                                        <div className="Notice__content--name"><details className='detail'>
                                            <summary>컴퓨터공학부 졸업 대체 요건</summary>
                                            <p className='context'><br />
                                                <div className='Notice__content--detail'><br /> 컴퓨터공학부 학생들은 졸업논문을 대체로 작성한다. <br />
                                                    논문을 대체하기 위해서는 ipp,일학습병행제가 이루어지고 있다. <br /><br /></div></p>
                                        </details> </div>
                                        <div className="Notice__content--date">2022-02-01 12:31</div>
                                    </div>

                                    <div className="Notice__content--content">
                                        <div className="Notice__content--number">2</div>
                                        <div className="Notice__content--category">안내</div>
                                        <div className="Notice__content--name"><details className='detail'>
                                            <summary>글로벌 비지니스 학부 졸업 대체 요건</summary>
                                            <p className='context'><br />
                                                <div className='Notice__content--detail'><br /> 졸업대체 : 토익 750 이상 <br /><br /></div></p>
                                        </details> </div>
                                        <div className="Notice__content--date">2022-04-01 02:31</div>
                                    </div>
                                    <div className="Notice__content--content">
                                        <div className="Notice__content--number">1</div>
                                        <div className="Notice__content--category">공지</div>
                                        <div className="Notice__content--name"><details className='detail'>
                                            <summary>공공인재학부 졸업 대체 요건</summary>
                                            <p className='context'><br />
                                                <div className='Notice__content--detail'><br /> 생긴지 얼마 안된 신설 학과랍니다 <br /><br /></div></p>
                                        </details> </div>
                                        <div className="Notice__content--date">2022-05-14 10:24</div>
                                    </div>



                    
                                </div></div>
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