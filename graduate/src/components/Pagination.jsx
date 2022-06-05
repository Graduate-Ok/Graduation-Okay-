import react, { useEffect, useState } from 'react';
import '../css/Board.css';

const Pagination = ({ page }) => {
    return (
        <div className="Board__page">
            <div className="Board__page--button">이전</div>
            <div className="Board__page--button" onClick={() => page()}>
                {page}
            </div>

            <div className="Board__page--button">다음</div>
        </div>
    );
};

export default Pagination;
