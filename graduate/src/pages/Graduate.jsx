import '../css/Graduate.css';
import '../css/ContentsPage.css';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from 'react';

// input type 커스터 마이징 -> https://helloinyong.tistory.com/275
/* 
투두
    방법 및 링크 다시 넣어주기. 
*/


// 설명페이지
const Graduate = () => {
    const [file, setFile] = useState(null);

    const handleChangeFile = (event) =>{
        console.log(event.target.files)
        setFile(event.target.files);

        const fd = new FormData();
        const getFile = document.getElementById("input-file");
        fd.append("input-file", getFile.files[0]);
        axios.post('http://localhost:8089/Graduate', fd,{
            headers : {
                'Content-Type' : `multipart/form-data`,
            }
        })
        .then(() => {
            console.log("success");
        })
        .catch((error) =>{
            console.log(error);
        })
    }

   
    return (
        <>
            <main>
                <section className="section">
                    <div className="section__text">
                        당신은 졸업이 가능한가요 ?
                    </div>
                    <form>
                        <div className="section__button">
                            <label className="upload__button" for="input-file" >학업성적확인서 PDF 업로드 </label>
                            <br /> <input type="file" accept='.pdf' id='input-file' name = "input-file" onChange = {handleChangeFile} multiple = "multiple" style={{ display: "none" }} />
                        </div>
                    </form>

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
                        
                        <Link to="GraduateOk" className="graduation__check--button" >결과 조회</Link>
                    </div>

                </section>
            </main>
        </>
    );
}

export default Graduate;