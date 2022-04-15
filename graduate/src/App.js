import * as React from 'react'
import { Reset } from 'styled-reset'
import './css/App.css';
import Header from './components/Header';
import Nav from './components/Nav';

// const App = () => (
//   <React.Fragment>
//     <Reset />
//     <div>Hi, I'm an app!</div>
//   </React.Fragment>
// )

function App(){
  return (
    <React.Fragment>
      <Reset/>
      <Header/>
      <Nav/>
     
    </React.Fragment>
    
    );
}
 
export default App;
