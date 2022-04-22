import react ,{ useState , useCallback}from 'react';
import Graduate from './Graduate';
import KyRecommend from './KyRecommend';
import '../css/Nav.css';

// 컨텐츠 페이지 
function MainPage(){
    const [option , setOption] = useState('');
    

    return (
        <>
        <nav className = "navbar">
                <div className="navbar__graduate" >
                    졸업요건확인
                </div>
                <div className="navbar__recommend">
                    교양추천받기
                </div>
                <div className="navbar__board">
                    게시판
                </div>
        </nav> 
        {
            option === '' ? <Graduate/>  : <KyRecommend/>
        }
            
        </>
    );
}
export default MainPage;