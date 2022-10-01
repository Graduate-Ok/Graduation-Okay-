import React from 'react';

/**
 * @description Notice 글 목록 컴포넌트
 */
const NoticeRow = ({ Notice }) => {
    return (
        <>
            <div className="Notice__content--content">
                <div className="Notice__content--number">{Notice.notiKey}</div>
                <div className="Notice__content--category">
                    {Notice.notiCategory}
                </div>
                <div className="Notice__content--name">
                    <details className="detail">
                        <summary>{Notice.notiTitle}</summary>
                        <p className="context">
                            <br />
                            <div className="Notice__content--detail">
                                <br /> {Notice.notiContent} <br />
                                <br />
                                <br />
                            </div>
                        </p>
                    </details>
                </div>
                <div className="Notice__content--date">
                    {Notice.notiWtTime.toString().substring(0, 10)}
                </div>
            </div>
        </>
    );
};

export default NoticeRow;
