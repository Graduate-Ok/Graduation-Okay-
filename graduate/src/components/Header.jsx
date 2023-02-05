import '../css/Header.css';

/**
 * @description Header 컴포넌트
 */
function Header() {
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
            </header>
        </>
    );
}

export default Header;
