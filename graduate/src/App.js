import * as React from 'react';
import {  Route, Routes} from 'react-router-dom';
import { Reset } from 'styled-reset';
import './css/App.css';
import Header from './components/Header';
import ContentsPage from './pages/ContentsPage';
import Footer from './components/Footer';
import EditBoard from './pages/EditBoard';
import GraduateOk from './pages/GraduateOk';


const App = () => {
  return (
    <React.Fragment>
            <Reset/>
            <Header/>
            <Routes>
              <Route path = "" exact element = {<ContentsPage/>}/>
              <Route path = "EditBoard" exact element = {<EditBoard/>}/>
              <Route path = "GraduateOk" exact element = {<GraduateOk/>}/>
            </Routes>
            <Footer/>
    </React.Fragment>
    );
}
export default App;
