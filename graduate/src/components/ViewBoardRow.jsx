import React from 'react';

const ViewBoardRow = ({ ViewBoard }) => {
    return (

        <div>
            <div className="Board__info">
                <span>작성자 :</span> <span className='BRD_WRITER'>{ViewBoard.brdWriter} </span>
                <span>&nbsp;작성일 :</span> <span className='BRD_WT_TIME'>{ViewBoard.brdWtTime.substring(0, 10) + ' ' + ViewBoard.brdWtTime.substring(11,16)} </span>
                <span>&nbsp; 조회수 :</span> <span className='BRD_LOOKUP'>{ViewBoard.brdLookup} </span>
            </div>

            <div class Name='Board__viewcontainer'>
                <div id="BRD_title"> {ViewBoard.brdTitle} </div> <br />
                <div id="BRD_content"> {ViewBoard.brdContent} </div>
            </div>

           
        </div>
    )


}

export default ViewBoardRow;