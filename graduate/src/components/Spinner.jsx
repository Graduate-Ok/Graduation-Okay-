import React from "react";
import {Background, LoadingText} from './Styles';
import Spinnerimg from './spinnerimg.gif';

function Spinner() {
  return (
    <Background>
      <LoadingText>잠시만 기다려 주세요.</LoadingText>
      <img src={Spinnerimg} alt="로딩중" width="5%" />
    </Background>
  );
}

export default Spinner;