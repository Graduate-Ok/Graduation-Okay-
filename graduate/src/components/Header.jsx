import '../css/Header.css';
import toggleState from '../atom';
import { useRecoilState } from 'recoil';

/**
 * @description Header 컴포넌트
 */
function Header() {
    const [isToggled, setIsToggled] = useRecoilState(toggleState);
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
                            alt="reject"
                            src="imgs/reject.png"
                            className="header__sidebar-icon"
                            onClick={handleToggleSide}
                        ></img>
                    </div>
                ) : (
                    <div className="header__sidebar">
                        <img
                            className="header__sidebar-icon"
                            src="imgs/sidebar.png"
                            alt="sidebar"
                            onClick={handleToggleSide}
                        ></img>
                    </div>
                )}
            </header>
        </>
    );
}

export default Header;
