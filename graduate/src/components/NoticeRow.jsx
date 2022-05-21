import React from 'react';

const NoticeRow = ({Notice}) => {
    return(
        <div className="Notice__content--content">
        <div className="Notice__content--number">{Notice.notiKey}</div>
        <div className="Notice__content--category">공지</div>
        <div className="Notice__content--name"><details className='detail'>
            <summary>{Notice.notiTitle}</summary>
            <p className='context'><br />
                <div className='Notice__content--detail'><br /> {Notice.notiContent} <br />
                    {Notice.notiCategory} <br /><br /></div></p>
        </details> </div>
        <div className="Notice__content--date">{Notice.notiWtTime}</div>
    </div>
    )
}

export default NoticeRow;