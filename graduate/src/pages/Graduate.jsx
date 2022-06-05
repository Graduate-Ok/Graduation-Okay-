import '../css/Graduate.css';
import '../css/ContentsPage.css';
import { Link, useNavigate } from 'react-router-dom';
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
    const [mileage, setMileage] = useState(0);
    const [kyCredit, setkyCredit] = useState(0);
    const [majorCredit, setMajorCredit] = useState(0);
    const [nonSubject, setNonSubject] = useState(0);
    const [failure, setFailure] = useState('');
    const [totalCredit, setTotalCredit] = useState(0);
    const [graduateok, setGraduateok] = useState('졸업 되려나..');

    // 데이터를 입력받아서 보여주는 것
    const handleChangeFile = (event) => {
        console.log(event.target.files);
        setFile(event.target.files);

        const fd = new FormData();
        const getFile = document.getElementById('file');
        fd.append('file', getFile.files[0]);
        axios
            .post('http://localhost:8089/Graduate', fd, {
                headers: {
                    'Content-Type': `multipart/form-data`,
                },
            })
            .then((response) => {
                console.log('success');
                setMileage(response.data.mileage);
                setkyCredit(response.data.kyCredit);
                setMajorCredit(response.data.majorCredit);
                setNonSubject(response.data.nonSubject);
                setTotalCredit(response.data.totalCredit);
                setFailure(response.data.failure);

                if (failure === '') {
                    setGraduateok('졸업 가능!');
                } else {
                    setGraduateok('졸업 불가능');
                }
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <>
            <main>
                <section className="section">
                    <div className="section__text">
                        당신은 졸업이 가능한가요 ?
                    </div>
                    <form method="post" action="/Graduate">
                        <div className="section__button">
                            <label className="upload__button" for="file">
                                학업성적확인서 PDF 업로드
                            </label>
                            <br />
                            <input
                                type="file"
                                accept=".pdf"
                                id="file"
                                name="file"
                                onChange={handleChangeFile}
                                multiple="multiple"
                                style={{ display: 'none' }}
                            />
                        </div>
                    </form>
                    <br />
                    <div className="pdf__position">
                        ❓ 학업성적확인서 PDF ❓ <br />
                        <br />
                        👉한신대학교 종합정보시스템
                        <br />
                        👉인트라넷
                        <br />
                        👉학부생서비스
                        <br />
                        👉성적
                        <br />
                        👉학업성적확인서
                        <br />
                        👉Save
                        <br />
                    </div>

                    <div className="Graduate__check">
                        <div className="Graduate_imPossible">
                            <br /> [ {graduateok} ] <br />
                            <br />
                        </div>

                        <div>
                            <table className="OkTable">
                                <tr className="Ok__menu">
                                    <th className="Lack__Graduate__Credit">
                                        이수 학점
                                    </th>
                                    <th className="Lack__MajorCredit">
                                        전공 학점
                                    </th>
                                    <th className="Lack__KyCredit">
                                        교양 학점
                                    </th>
                                    <th className="Lack__NonSub">
                                        비교과 이수 학기
                                    </th>
                                    <th className="Lack__mileage">마일리지</th>
                                </tr>

                                <tr>
                                    <td className="Lack__Graduate__Credit">
                                        {totalCredit}
                                    </td>
                                    <td className="Lack__MajorCredit">
                                        {majorCredit}
                                    </td>
                                    <td className="Lack__KyCredit">
                                        {kyCredit}
                                    </td>
                                    <td className="Lack__NonSub">
                                        {nonSubject}
                                    </td>
                                    <td className="Lack__mileage">{mileage}</td>
                                </tr>
                            </table>
                        </div>

                        <div className="Graduate_lack">{failure}</div>
                    </div>
                </section>
            </main>
        </>
    );
};

export default Graduate;
