import react , {useState} from 'react';
import Graduate from './Graduate';
import KyRecommend from './KyRecommend';
import Board from './Board';
import Notice from './Notice';
import '../css/Nav.css';
import '../css/MainPage.css';

 let now = {
    Notice : <Notice/>,
    Graduate : <Graduate/>,
    Board : <Board/>,
    KyRecommend : <KyRecommend/>
 }

 let nowUI = 'Notice';


function handleClick(e){
    e.preventDefault();
    console.log("aa");
    let abcUI = e.target.value;
    let abc = {
        Notice : <Notice/>,
        Graduate : <Graduate/>,
        Board : <Board/>,
        KyRecommend : <KyRecommend/>
    }
    console.log(abcUI);
    console.log(abc);
}



// 컨텐츠 페이지 
function MainPage(){
    
    return (
        <>
        <nav className = "navbar">
                <div className="navbar__graduate navbar-size" onClick={handleClick} value = {Graduate}>
                    졸업요건확인
                </div>
                <div className="navbar__recommend navbar-size" onClick={handleClick} value = {KyRecommend}>
                    교양추천받기
                </div>
                <div className="navbar__board navbar-size" onClick={handleClick} value = {Board}>
                    게시판
                </div>
        </nav> 
        <div>
        {
           //now[nowUI]
        }
        </div>
        
       
            
        </>
    );
}
export default MainPage;