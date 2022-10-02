import react from 'react';
import { Link } from 'react-router-dom';
import '../css/Nav.css';
import '../css/ContentsPage.css';

/**
 * @description nav 컴포넌트
 */
function Nav() {
    return (
        <>
            <nav className="navbar">
                <Link to="Notice" className="navbar__nav">
                    공지사항
                </Link>
                <Link to="Graduate" className="navbar__nav">
                    졸업요건확인
                </Link>
                <Link to="KyRecommend" className="navbar__nav">
                    인기교양추천
                </Link>
                <Link to="Board" className="navbar__nav">
                    정보공유게시판
                </Link>
                <Link to="Feedback" className="navbar__nav">
                    피드백하기
                </Link>
            </nav>
        </>
    );
}
export default Nav;
