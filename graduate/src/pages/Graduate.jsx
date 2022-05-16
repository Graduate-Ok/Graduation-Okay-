import '../css/Graduate.css';
import '../css/ContentsPage.css';
import { Link, useLinkClickHandler } from 'react-router-dom';

// input type 커스터 마이징 -> https://helloinyong.tistory.com/275
/* 
투두
    방법 및 링크 다시 넣어주기. 
    

*/


// 설명페이지
function Graduate() {
    return (
        <>
            <main>
                <section className="section">
                    <div className="section__text">
                        당신은 졸업이 가능한가요 ?
                    </div>
                    <div className="section__button">
                        <label className="upload__button" for="input-file">학업성적확인서 PDF 업로드 </label>
                        <br /> <input type="file" accept='.pdf' id='input-file' style={{ display: "none" }} />
                    </div>

                    <br /><br /><br /><br />

                    <div className='pdf__position'> ❓ 학업성적확인서 PDF ❓ <br /><br />
                        👉한신대학교 종합정보시스템<br />
                        👉인트라넷<br />
                        👉학부생서비스<br />
                        👉성적<br />
                        👉학업성적확인서<br />
                        👉Save<br />
                    </div>
                    <br />
                    <div className="GraduateOk__footer">
                        <Link to="GraduateOk" className="graduation__check--button">결과 조회</Link>
                        <br /><br />
                    </div>

                </section>
            </main>
        </>
    );
}

export default Graduate;