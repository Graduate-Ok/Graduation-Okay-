import '../../css/MainPage.css';
import '../../css/Notice.css';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import NoticeRow from './NoticeRow';
import Pagination from '../../components/Pagination';
import { API_URL, PORT_NUMBER } from '../../utils/constant';

/**
 * @description notice 페이지 컴포넌트
 */
const Notice = () => {
    const [inputData, setInputData] = useState([]);
    const [srchType, setSrchType] = useState('');
    const [srchKeyword, setSrchKeyword] = useState('');
    const [page, setPage] = useState(1);
    const pageName = 'Notice';
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(
                `${API_URL}${PORT_NUMBER}/Notice/?srchType=${srchType}&srchKeyword=${srchKeyword}&page=${page}`,
            );
            setInputData(response.data.noticeDtoList);
        };
        fetchData();
    }, []);

    /**
     * @description 검색 쿼리 기능
     */
    const handleSubmit = async (e) => {
        e.preventDefault();
        const select = document.forms['searchBar']['srchType'].value;
        const search = document.forms['searchBar']['srchKeyword'].value;
        if (search === '') {
            alert('검색어를 입력해주세요!');
            const response = await axios.get(
                `${API_URL}${PORT_NUMBER}/Notice/`,
            );
            setInputData(response.data.noticeDtoList);
        } else {
            const response = await axios.get(
                `${API_URL}${PORT_NUMBER}/Notice/?srchType=${select}&srchKeyword=${search}`,
            );
            setInputData(response.data.noticeDtoList);
        }
    };

    /**
     * @description notice 탭
     */
    const handleClickTab = async (param) => {
        const response = await axios.get(
            `${API_URL}${PORT_NUMBER}/Notice/?notiCategory=${param}`,
        );
        setInputData(response.data.noticeDtoList);
    };

    /**
     * @description 페이지네이션 기능
     */
    const [searchHelper, setSearchHelper] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(
                `${API_URL}${PORT_NUMBER}/Notice/`,
            );
            setSearchHelper(response.data.searchHelper);
        };
        fetchData();
    }, []);

    const handleNextBtn = async (e) => {
        e.preventDefault();
        const response = await axios.get(
            `${API_URL}${PORT_NUMBER}/Notice/?page=${searchHelper.nextBlock}`,
        );
        setSearchHelper(response.data.searchHelper);
        setInputData(response.data.noticeDtoList);
    };
    const handlePrevBtn = async (e) => {
        e.preventDefault();
        const response = await axios.get(
            `${API_URL}${PORT_NUMBER}/Notice/?page=${searchHelper.prevBlock}`,
        );
        setSearchHelper(response.data.searchHelper);
        setInputData(response.data.noticeDtoList);
    };
    const handlePageClick = async (i) => {
        const response = await axios.get(
            `${API_URL}${PORT_NUMBER}/Notice/?page=${i}`,
        );
        setInputData(response.data.noticeDtoList);
    };

    return (
        <>
            <main>
                <div className="Notice">
                    <div className="NoticeSize">
                        <div className="Notice__header">
                            <p className="minititle">공지사항</p>
                        </div>

                        <div className="Notice__navbar">
                            <div></div>
                        </div>
                        {/*TAB*/}
                        <div class="tab">
                            <ul class="tabnav">
                                <li>
                                    <div
                                        onClick={() => {
                                            handleClickTab();
                                        }}
                                    >
                                        전체
                                    </div>
                                </li>

                                <li>
                                    <div
                                        onClick={() => {
                                            handleClickTab('notice');
                                        }}
                                    >
                                        공지
                                    </div>
                                </li>
                                <li>
                                    <div
                                        onClick={() => {
                                            handleClickTab('guide');
                                        }}
                                    >
                                        안내
                                    </div>
                                </li>
                            </ul>
                            <form
                                className="Notice__search"
                                name="searchBar"
                                onSubmit={handleSubmit}
                            >
                                <select name="srchType" id="srchType">
                                    <option value="title">제목</option>
                                    <option value="content">내용</option>
                                </select>
                                <input
                                    type="text"
                                    placeholder="검색어를 입력하세요"
                                    name="srchKeyword"
                                    id="srchKeyword"
                                ></input>
                                <input
                                    type="submit"
                                    className="Notice__search--button"
                                    id="submit"
                                    name="submit"
                                    value="검 색"
                                ></input>
                            </form>
                            <div class="tabcontent">
                                <div className="Notice__content--title">
                                    <div className="Notice__content--number">
                                        번호
                                    </div>
                                    <div className="Notice__content--category">
                                        카테고리
                                    </div>
                                    <div className="Notice__content--name">
                                        제목
                                    </div>
                                    <div className="Notice__content--date">
                                        날짜
                                    </div>
                                </div>
                                {inputData.map((inputData) => {
                                    return <NoticeRow Notice={inputData} />;
                                })}
                            </div>
                            <div className="Board__page">
                                <Pagination
                                    handlePageClick={handlePageClick}
                                    page={searchHelper.page}
                                    searchHelper={searchHelper}
                                    handleNextBtn={handleNextBtn}
                                    handlePrevBtn={handlePrevBtn}
                                    pageName={pageName}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </>
    );
};

export default Notice;
