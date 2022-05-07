import react , {useState} from 'react';
import Graduate from './Graduate';
import KyRecommend from './KyRecommend';
import Board from './Board';
import Notice from './Notice';
import Mainpage from './Mainpage';
import '../css/Nav.css';
import '../css/ContentsPage.css';


// nav + contents  
function ContentsPage(){
    const [current, setCurrent] = useState("Mainpage");
    
    const handleClick = comp =>{
        setCurrent(comp);
        console.log(comp);
    }


    return (
        <>
        <nav className = "navbar">
                <div className="navbar__nav" onClick={() =>handleClick("Notice")} >
                    공지사항
                </div>
                <div className="navbar__nav" onClick={() =>handleClick("Graduate")} >
                    졸업요건확인
                </div>
                <div className="navbar__nav" onClick={() =>handleClick("KyRecommend")}>
                    교양추천받기
                </div>
                <div className="navbar__nav" onClick={() =>handleClick("Board")} >
                    게시판
                </div>
                
        </nav> 
        {
           current === "Mainpage" ? <Mainpage/> : current === "Notice" ? <Notice/> : current === "Graduate" ? <Graduate/> : current ==="KyRecommend" ? <KyRecommend/> : <Board/>
        }
        
        </>
    );
}
export default ContentsPage;