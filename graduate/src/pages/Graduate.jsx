import react from 'react';
import '../css/Graduate.css';
import '../css/ContentsPage.css';

// 설명페이지
function Graduate(){
    return (
        <> 
            <main>
            <section className = "section">
                    <div className = "section__text">
                        아래 버튼누르면 졸업 확인 즉$시$가$능
                    </div>
                    <div className="section__button">
                        <button> pdf로 학업성적확인서 제출하기</button>
                    </div>
                </section>
            </main>
        </>
    );
}

export default Graduate;