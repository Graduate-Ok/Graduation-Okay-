import '../css/Footer.css';

/**
 * @description Footer 컴포넌트 페이지
 */
function Footer() {
    return (
        <footer className="footer">
            <div className="footer__section">
                <div className="section-content">
                    <p>바로가기</p>
                    <a href="https://www.hs.ac.kr/intro.html" target="_blank">
                        한신대학교 홈페이지
                    </a>
                    <a
                        href="https://hsctis.hs.ac.kr/app-nexa/index.html"
                        target="_blank"
                    >
                        학업성적확인서
                    </a>
                </div>
                <div className="section-content">
                    <p>TEAM 졸업가능?</p>
                    <p>🙂 배성규 박수빈 김지윤 김민석 🙂</p>
                    <p>https://github.com/Graduate-Ok/Graduation-Okay-</p>
                </div>
            </div>
        </footer>
    );
}

export default Footer;
