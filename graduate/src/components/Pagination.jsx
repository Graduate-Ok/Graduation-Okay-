import react, {useEffect, useState } from 'react';
import axios from 'axios';
import '../css/Board.css';

const Pagination = ({ limit, currentPage, onPageChange }) =>{
    const [pageCount, setPageCount] = useState(0);
    const [pages, setPages] = useState([]);

 

    useEffect(() =>{
        const fetchData = async() =>{
            const response = await axios.get('http://localhost:8089/Notice/');
            const length = response.data.length;
            const tmpCnt = Math.ceil(length / limit);
            setPageCount(tmpCnt);
            const tmpPages = Array.from({length : tmpCnt}, (v,i) => i +1);
            setPages(tmpPages);
        }
    })

    if(pageCount === 1) return null;

    return (
        <div className="Board__page">
            <div className="Board__page--button">이전</div>
            <div className="Board__page--button">1</div>
            <div className="Board__page--button">2</div>
            <div className="Board__page--button">3</div>
            <div className="Board__page--button">4</div>
            <div className="Board__page--button">5</div>
            <div className="Board__page--button">6</div>
            <div className="Board__page--button">7</div>
            <div className="Board__page--button">8</div>
            <div className="Board__page--button">9</div>
            <div className="Board__page--button">다음</div>
        </div>
    )
}

export default Pagination;