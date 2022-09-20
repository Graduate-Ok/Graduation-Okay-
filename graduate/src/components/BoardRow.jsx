import React from 'react';
import { Link } from 'react-router-dom';

/**
 *
 * @description BoardRow 컴포넌트
 */
const BoardRow = ({ Board }) => {
    return (
        <div className="Board__content--content">
            <div className="Board__content--number">{Board.brdKey}</div>
            <Link to={'' + Board.brdKey} className="Board__content--name">
                {Board.brdTitle}
            </Link>
            <div className="Board__content--writer">{Board.brdWriter}</div>
            <div className="Board__content--date">
                {Board.brdWtTime.toString().substring(0, 10)
                    + ' '
                    + Board.brdWtTime.toString().substring(11, 16)}
            </div>
            <div className="Board__content--hits">{Board.brdLookup}</div>
        </div>
    );
};

export default BoardRow;
