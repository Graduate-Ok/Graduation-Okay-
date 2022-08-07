import * as React from 'react';
import { Route, Routes } from 'react-router-dom';
import { Reset } from 'styled-reset';
import './css/App.css';
import Header from './components/Header';
import Nav from './pages/Nav';
import Footer from './components/Footer';
import PostBoard from './pages/PostBoard';
import Graduate from './pages/Graduate';
import Notice from './pages/Notice';
import Mainpage from './pages/Mainpage';
import Board from './pages/Board';
import ViewBoard from './pages/ViewBoard';
import KyRecommend from './pages/KyRecommend';
import NotFound from './pages/NotFound';
import EditBoard from './pages/EditBoard';

const App = () => {
    return (
        <React.Fragment>
            <Reset />
            <Header />
            <Nav />
            <Routes>
                <Route path="" exact element={<Mainpage />} />
                <Route path="Notice" exact element={<Notice />} />
                <Route path="Notice/?page=:page" exact element={<Notice />} />
                <Route path="Graduate" exact element={<Graduate />} />
                <Route path="KyRecommend" exact element={<KyRecommend />} />
                <Route path="Board" exact element={<Board />} />
                <Route path="Board/?page=:page" exact element={<Board />} />
                <Route path="Board/PostBoard" exact element={<PostBoard />} />
                <Route path="Board/EditBoard" exact element={<EditBoard />} />
                <Route path="Board/:brdKey" exact element={<ViewBoard />} />
                <Route path="*" element={<NotFound />} />
            </Routes>
            <Footer />
        </React.Fragment>
    );
};
export default App;
