import React from 'react';

/**
 *
 * @description ViewBoardRow 컴포넌트
 */
const ViewBoardRow = ({ ViewBoard }) => {
    return (
        <div>
            <div className="Board__info">
                <span>작성자 :</span>
                <span className="BRD_WRITER">
                    &nbsp;{ViewBoard.brdWriter} &nbsp;
                </span>
                <span>&nbsp;작성일 :</span>
                <span className="BRD_WT_TIME">
                    &nbsp;
                    {ViewBoard.brdWtTime.substring(0, 10) +
                        ' ' +
                        ViewBoard.brdWtTime.substring(11, 16)}
                    &nbsp;
                </span>
                <span>&nbsp; 조회수 :</span>
                <span className="BRD_LOOKUP">
                    &nbsp;{ViewBoard.brdLookup} &nbsp;
                </span>
            </div>

            <div className="BRD_title"> {ViewBoard.brdTitle} </div>
            <div className="BRD_content"> {ViewBoard.brdContent} </div>
        </div>
    );
};

export default ViewBoardRow;
