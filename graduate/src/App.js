import * as React from 'react'
import { Reset } from 'styled-reset'
import './css/App.css';
import Header from './components/Header';
import ContentsPage from './pages/ContentsPage';
import Footer from './components/Footer';


function App(){
  return (
    <React.Fragment>
          <Reset/>
          <Header/>
         
          <ContentsPage/>
          <Footer/>
    </React.Fragment>
    );
}
export default App;
