import '../css/Header.css';
import Sidebar from './Sidebar';
import { Link } from 'react-router-dom';
import { useState } from 'react';

/**
 * @description Header 컴포넌트
 */
function Header() {
    const [isToggled, setIsToggled] = useState(false);
    const handleToggleSide = () => {
        setIsToggled(!isToggled);
    };
    return (
        <>
            <header className="header">
                <a href="/">
                    <img
                        className="header-img"
                        src="imgs/logo.png"
                        alt="headerimg"
                    ></img>
                </a>

                {isToggled ? (
                    <div className="header__sidebar">
                        <img
                            className="header__sidebar-icon"
                            src="imgs/sidebar.png"
                            alt="icon"
                            onClick={handleToggleSide}
                        ></img>
                    </div>
                ) : (
                    <div className="header__sidebar--toggleMode">
                        <div>
                            <div>
                                <img
                                    alt="reject"
                                    src="imgs/reject.png"
                                    className="header__sidebar-reject"
                                    onClick={handleToggleSide}
                                ></img>
                            </div>
                            <Link to="Notice" className="navbar__nav">
                                공지사항
                            </Link>
                            <Link to="KyRecommend" className="navbar__nav">
                                인기교양추천
                            </Link>
                            <Link to="Graduate" className="navbar__nav">
                                졸업요건확인
                            </Link>
                            <Link to="Board" className="navbar__nav">
                                정보공유게시판
                            </Link>
                            <Link to="Feedback" className="navbar__nav">
                                피드백하기
                            </Link>
                        </div>
                    </div>
                )}
            </header>
        </>
    );
}

export default Header;
