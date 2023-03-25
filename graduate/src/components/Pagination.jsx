import react from 'react';
import '../css/Board.css';

/**
 * @description 페이지네이션 컴포넌트
 */

const Pagination = ({ searchHelper, pageName, handleButton }) => {
    const paging = () => {
        const array = [];
        for (let i = searchHelper.startPage; i <= searchHelper.endPage; i++) {
            array.push(
                <div
                    className="Board__page--button"
                    onClick={(e) => handleButton(e, i)}
                >
                    {i}
                </div>,
            );
        }
        return array;
    };

    return (
        <>
            <div
                className="Board__page--button"
                onClick={(e) => handleButton(e, searchHelper.prevBlock)}
            >
                이전
            </div>
            {paging()}
            <div
                className="Board__page--button"
                onClick={(e) => handleButton(e, searchHelper.nextBlock)}
            >
                다음
            </div>
        </>
    );
};

export default Pagination;
