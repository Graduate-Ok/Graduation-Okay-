import react ,{ useState, useCallback }from 'react';
import Graduate from './Graduate';
import KyRecommend from './KyRecommend';
import Board from './Board';
import '../css/Nav.css';
import '../css/MainPage.css';

 let now = {
    Graduate : <Graduate/>,
    Board : <Board/>,
    KyRecommend : <KyRecommend/>
 }

 let nowUI = '';

function handleClick(){

}


// 컨텐츠 페이지 
function MainPage(){
    
    return (
        <>
        <nav className = "navbar">
                <div className="navbar__graduate" onClick={handleClick}>
                    졸업요건확인
                </div>
                <div className="navbar__recommend" onClick={handleClick}>
                    교양추천받기
                </div>
                <div className="navbar__board" onClick={handleClick}>
                    게시판
                </div>
        </nav> 
        <div>
        {
           now[nowUI] 
        }
        </div>
        <div className='main__background'>
            <div className = "main__contentbox">
                <img className = "main__contentbox-img" src = "imgs/background.jpg" alt = "background"></img>
            </div>
        </div>
       
            
        </>
    );
}
export default MainPage;