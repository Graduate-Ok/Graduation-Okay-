import * as React from 'react'
import { Reset } from 'styled-reset'
import './css/App.css';
import Header from './components/Header';
import MainPage from './pages/MainPage';
import Footer from './components/Footer';
import Graduate from './pages/Graduate';
import KyRecommend from './pages/KyRecommend';


function App(){
  return (
    <React.Fragment>
          <Reset/>
          <Header/>
         
          <MainPage/>
          <Footer/>
    </React.Fragment>
    );
}
export default App;
