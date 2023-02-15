import { Link } from 'react-router-dom';
import toggleState from '../atom';
import { useRecoilState } from 'recoil';
import '../css/Nav.css';
import '../css/MainPage.css';
import { useEffect, useState } from 'react';

/**
 * @description nav 컴포넌트
 */
function Nav() {
    const [checkToggled, setCheckToggled] = useRecoilState(toggleState);
    const [checkWidth, setCheckWidth] = useState(window.innerWidth);

    const clickLink = () => {
        setCheckToggled(false);
    };
    const handleResize = () => {
        setCheckWidth(window.innerWidth);
    };
    useEffect(() => {
        window.addEventListener('resize', handleResize);
    });
    return (
        <>
            {checkToggled || checkWidth >= 768 ? (
                <nav className="navbar">
                    <Link
                        to="Notice"
                        className="navbar__nav"
                        onClick={clickLink}
                    >
                        공지사항
                    </Link>
                    <Link
                        to="KyRecommend"
                        className="navbar__nav"
                        onClick={clickLink}
                    >
                        인기교양추천
                    </Link>
                    <Link
                        to="Graduate"
                        className="navbar__nav"
                        onClick={clickLink}
                    >
                        졸업요건확인
                    </Link>
                    <Link
                        to="Board"
                        className="navbar__nav"
                        onClick={clickLink}
                    >
                        정보공유게시판
                    </Link>
                    <Link
                        to="Feedback"
                        className="navbar__nav"
                        onClick={clickLink}
                    >
                        피드백하기
                    </Link>
                </nav>
            ) : (
                <></>
            )}
        </>
    );
}
export default Nav;
