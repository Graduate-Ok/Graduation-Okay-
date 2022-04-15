import react from 'react';
import '../css/Header.css';

// 웹사이트 상단 바
function Header() {
    return (
        <> 
            <header className = "Header">
                <div>
                    <a href="/">
                        <img src = "imgs/logo.png" alt = "headerimg"></img>
                    </a>
                </div>
            </header>
        </>
    );
}

export default Header;