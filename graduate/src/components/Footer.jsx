import react from 'react';
import '../css/Footer.css';


function Footer(){
    return(
        <div id = "wrap">
        <footer className='footer'>
            <div className = "footer__section">
                <div className = 'section-content'>
                    <p> Github : https://github.com/pangkyu/Graduation-Okay- </p>                    
                </div>
                <div className = 'section-content'>
                    <p>TEAM 졸업가능?</p>
                    <p>배성규 박수빈</p>
                    <p>김지윤 김민석</p>
                </div>
            </div>
        </footer>
        </div>
    );
}

export default Footer;