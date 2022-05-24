import React from 'react';

const BoardRow = ({Board}) => {
    console.log(Board.brdKey);
    return(
        <div className="Board__content--content">
            <div className="Board__content--number">{Board.brdKey}</div>
            <div className="Board__content--name">{Board.brdTitle}</div>
            <div className="Board__content--writer">{Board.brdWriter}</div>
            <div className="Board__content--date">{Board.brdWtTime.substring(0, 10) + ' ' + Board.brdWtTime.substring(11,16)}</div>
            <div className="Board__content--hits">{Board.brdLookup}</div>
        </div>
    )
}

export default BoardRow;