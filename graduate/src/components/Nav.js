import react from 'react';
import { Link } from 'react-router-dom';
import '../css/Nav.css';

// 웹사이트 상단 바
function Nav() {
    return (
        <> 
        <nav className = "navbar">
                <div className="navbar__graduate">
                    졸업요건확인
                </div>
                <div className="navbar__recommend">
                    교양추천받기
                </div>
           
        </nav>
        </>
    );
}

export default Nav;