import react from 'react';
import '../css/Board.css';
import { Link } from 'react-router-dom';

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
                <Link
                    to={`/${pageName}/?page=${page}`}
                    className="Board__page--button"
                    onClick={(e) => handlePageClick(i, e)}
                >
                    {i}
                </Link>,
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
