import react from 'react';
import '../css/Board.css';
import { Link } from 'react-router-dom';

const Pagination = ({ totalPageCnt, pageSize, handlePageClick, page }) => {
    const paging = () => {
        let array = [];
        for (let i = 1; i <= pageSize; i++) {
            if (i === totalPageCnt + 1) {
                break;
            } else {
                // array.push(
                //     <div
                //         className="Board__page--button"
                //         onClick={(e) => handlePageClick(i, e)}
                //     >
                //         {i}
                //     </div>,
                // );
                array.push(
                    <Link
                        to={'/Board/?page=' + page}
                        className="Board__page--button"
                        onClick={(e) => handlePageClick(i, e)}
                    >
                        {i}
                    </Link>,
                );
            }
        }
        return array;
    };

    return <>{paging()}</>;
};

export default Pagination;
