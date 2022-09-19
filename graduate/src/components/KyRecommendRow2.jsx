import React from 'react';

/**
 *
 * @description 교양 추천 행 컴포넌트
 */
const KyRecommendRow2 = () => {
    return (
<div className="part1">
                    <br />
                    <div className="KyTableWrapper">
                        <table className="KyTable">
                            <tr className="menu">
                                <th className="grade_table_th rank_width">
                                    순위
                                </th>
                                <th className="grade_table_th name_width">
                                    과목명
                                </th>
                                <th className="grade_table_th ky_width">
                                    교양 인재상
                                </th>
                                <th className="grade_table_th core_width">
                                    핵심 역량
                                </th>
                                <th className="grade_table_th gradenum_width">
                                    학점
                                </th>
                                <th className="grade_table_th num_width">
                                    별점
                                </th>
                            </tr> 
                           
                           
     <tr className="KyRecommendWrapper">
             <td className="grade_table_td ">🥇</td>
            <td className="grade_table_td name_width">진로선택과취업준비</td>
            <td className="grade_table_td ky_width">도전하는창의인</td>
            <td className="grade_table_td core_width">창의융합</td>
            <td className="grade_table_td gradenum_width">3
            </td>
            <td className="grade_table_td num_width">5.0</td> 
    </tr>

    <tr className="KyRecommendWrapper">
             <td className="grade_table_td ">🥈</td>
            <td className="grade_table_td name_width">수재수학:수포자를위한재미있는수학</td>
            <td className="grade_table_td ky_width">도전하는창의인</td>
            <td className="grade_table_td core_width">지식정보</td>
            <td className="grade_table_td gradenum_width">3
            </td>
            <td className="grade_table_td num_width">4.16</td> 
    </tr>

    <tr className="KyRecommendWrapper">
             <td className="grade_table_td ">🥉</td>
            <td className="grade_table_td name_width">정조문화권의문화유산과지역정체성</td>
            <td className="grade_table_td ky_width">소통하는 지성인</td>
            <td className="grade_table_td core_width">인문</td>
            <td className="grade_table_td gradenum_width">3
            </td>
            <td className="grade_table_td num_width">3.32</td> 
    </tr>
                            
                        </table>
                    </div>
             
        
       
        </div>
    );
};



export default KyRecommendRow2;