import react from 'react';
import '../css/Board.css';

/**
 * @description 페이지네이션 컴포넌트
 */

const Pagination = ({
    handlePageClick,
    page,
    searchHelper,
    handleNextBtn,
    handlePrevBtn,
    pageName,
}) => {
    const paging = () => {
        let array = [];
        for (let i = searchHelper.startPage; i <= searchHelper.endPage; i++) {
            array.push(
                <div
                    className="Board__page--button"
                    onClick={(e) => handlePageClick(i, e)}
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
                onClick={(e) => handlePrevBtn(e)}
            >
                이전
            </div>
            {paging()}
            <div
                className="Board__page--button"
                onClick={(e) => handleNextBtn(e)}
            >
                다음
            </div>
        </>
    );
};

export default Pagination;
