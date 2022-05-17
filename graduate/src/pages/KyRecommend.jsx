import react from 'react';
import '../css/KyRecommend.css';

// 교양과목을 추천해주는 페이지 
function KyRecommend() {
    return (
        <>
            <main>
                <div className='main'>
                    <div className='title'>✨인기 교양 추천✨</div>
                    <br />
                    <div>
                        <table className='KyTable'>
                            <tr className='menu'>
                                <th className="grade_table_th rank_width" >순위</th>
                                <th className="grade_table_th name_width" >과목명</th>
                                <th className="grade_table_th ky_width" >교양 인재상</th>
                                <th className="grade_table_th core_width" >핵심 역량</th>
                                <th className="grade_table_th gradenum_width" >학점</th>
                                <th className="grade_table_th num_width" >수강횟수</th>
                            </tr>

                            <tr>
                                <td className="grade_table_td rank_width">1</td>
                                <td className="grade_table_td name_width">오르간연주법1</td>
                                <td className="grade_table_td ky_width">소통하는 지성인</td>
                                <td className="grade_table_td core_width">인문</td>
                                <td className="grade_table_th gradenum_width" >2</td>
                                <td className="grade_table_td num_width">1000</td>
                            </tr>

                            <tr>
                                <td className="grade_table_td rank_width">2</td>
                                <td className="grade_table_td name_width">감정수업:침묵과말</td>
                                <td className="grade_table_td ky_width">도전하는 창의인</td>
                                <td className="grade_table_td core_width">창의융합</td>
                                <td className="grade_table_th gradenum_width" >3</td>
                                <td className="grade_table_td num_width">1000</td>
                            </tr>

                            <tr>
                                <td className="grade_table_td rank_width">3</td>
                                <td className="grade_table_td name_width">기초영어회화1 </td>
                                <td className="grade_table_td ky_width">실천하는 평화인</td>
                                <td className="grade_table_td core_width">글로벌</td>
                                <td className="grade_table_th gradenum_width" >3</td>
                                <td className="grade_table_td num_width">900</td>
                            </tr>

                            <tr>
                                <td className="grade_table_td rank_width">4</td>
                                <td className="grade_table_td name_width">물리란무엇인가</td>
                                <td className="grade_table_td ky_width">도전하는 창의인</td>
                                <td className="grade_table_td core_width">지식정보</td>
                                <td className="grade_table_th gradenum_width" >3</td>
                                <td className="grade_table_td num_width">800</td>
                            </tr>

                        </table>
                    </div>

                </div>
            </main>
        </>
    );
}

export default KyRecommend;