import react, {useEffect, useState } from 'react';
import '../css/Board.css';

const Pagination = ({postsPerPage , totalPosts, paginate}) =>{
    
    const pageNumbers = [];
     // page 번호 * 10 - 10 (setState 설정하고 호출 )
    // ,총 글 개수 35개면 35 / 10 => 3.5 => 4페이지 생성된다 
    for (let i = 1; i <= Math.ceil(totalPosts / postsPerPage); i++){
        pageNumbers.push(i);
    }
    

    return (
      
     

        <div className="Board__page">
            <div className="Board__page--button">이전</div>
            {pageNumbers.map((number) =>(
                <div className="Board__page--button" onClick = {() => paginate(number)}>{number}</div>
            ))}
            
            <div className="Board__page--button">다음</div>
        </div>
      
        
        
    )
}

export default Pagination;