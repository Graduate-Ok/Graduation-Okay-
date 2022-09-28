import { useNavigate, useParams } from 'react-router-dom';
import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import useInput from '../hooks/useInput';
import EditBoardRow from '../components/EditBoardRow';

/**
 *
 * @description 글 수정 페이지 컴포넌트
 */
const EditBoard = ({ brdKey }) => {
    const navigate = useNavigate();

    const [inputData, setInputData] = useState([]);

    let params = useParams().brdKey;
    useEffect(() => {
        const fetchData = async () => {
            const response = await axios.get(
                `http://13.125.25.62:8089/Board/${params}`,
            );
            setInputData(response.data);
        };
        fetchData();
    }, []);

    console.log(params);

    return (
        <>
            <main>
                <div className="Board">
                    <div className="BoardSize">
                        <div className="Board__header">
                            <p className="minititle"> 정보공유 게시판</p>
                        </div>

                        {inputData.map((inputData) => {
                            return (
                                <EditBoardRow
                                    EditBoard={inputData}
                                    key={inputData.brdKey}
                                />
                            );
                        })}
                    </div>
                </div>
            </main>
        </>
    );
};

export default EditBoard;
