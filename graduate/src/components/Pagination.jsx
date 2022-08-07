import react from 'react';
import '../css/Board.css';

const Pagination = ({ totalPageCnt, pageSize, handlePageClick }) => {
    const paging = () => {
        let array = [];
        for (let i = 1; i <= pageSize; i++) {
            if (i === totalPageCnt + 1) {
                break;
            } else {
                array.push(
                    <div
                        className="Board__page--button"
                        onClick={(e) => handlePageClick(i, e)}
                    >
                        {i}
                    </div>,
                );
            }
        }
        return array;
    };

    return <>{paging()}</>;
};

export default Pagination;
