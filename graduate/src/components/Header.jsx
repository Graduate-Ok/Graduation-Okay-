import '../css/Header.css';

/**
 *
 * @description Header 컴포넌트
 */
function Header() {
    return (
        <>
            <header className="Header">
                <div>
                    <a href="/">
                        <img src="imgs/logo.png" alt="headerimg"></img>
                    </a>
                </div>
            </header>
        </>
    );
}

export default Header;
