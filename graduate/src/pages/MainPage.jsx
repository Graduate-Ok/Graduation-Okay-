import react , {useState} from 'react';
import Graduate from './Graduate';
import KyRecommend from './KyRecommend';
import Board from './Board';
import Notice from './Notice';
import '../css/Nav.css';
import '../css/MainPage.css';


// 컨텐츠 페이지 
function MainPage(){
    const [current, setCurrent] = useState("Notice");
    
    const handleClick = comp =>{
        setCurrent(comp);
        
        console.log(comp);
        //console.log(abc);
    }


    return (
        <>
        <nav className = "navbar">
                <div className="navbar__graduate navbar-size" onClick={() =>handleClick("Graduate")} >
                    졸업요건확인
                </div>
                <div className="navbar__recommend navbar-size" onClick={() =>handleClick("KyRecommend")}>
                    교양추천받기
                </div>
                <div className="navbar__board navbar-size" onClick={() =>handleClick("Board")} >
                    게시판
                </div>
        </nav> 
        <div>
        {
           current === "Notice" ? <Notice/> : current === "Graduate" ? <Graduate/> : current ==="KyRecommend" ? <KyRecommend/> : <Board/>
        }
        </div>
        
       
            
        </>
    );
}
export default MainPage;