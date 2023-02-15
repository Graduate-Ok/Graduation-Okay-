import * as React from 'react';
import { Route, Routes } from 'react-router-dom';
import { Reset } from 'styled-reset';
import { RecoilRoot } from 'recoil';
import './css/App.css';
import Header from './components/Header';
import Nav from './components/Nav';
import Footer from './components/Footer';
import PostBoard from './pages/Board/PostBoard/PostBoard';
import Graduate from './pages/Graduate/Graduate';
import Notice from './pages/Notice/Notice';
import Mainpage from './pages/Mainpage';
import Board from './pages/Board/Board';
import ViewBoard from './pages/Board/ViewBoard/ViewBoard';
import KyRecommend from './pages/KyRecommend/KyRecommend';
import NotFound from './pages/NotFound';
import EditBoard from './pages/Board/EditBoard/EditBoard';
import Feedback from './pages/Feedback';

const App = () => {
    return (
        <React.Fragment>
            <RecoilRoot>
                <Reset />
                <Header />
                <Nav />
                <Routes>
                    <Route path="" exact element={<Mainpage />} />
                    <Route path="Notice" exact element={<Notice />} />
                    <Route path="Graduate" exact element={<Graduate />} />
                    <Route path="KyRecommend" exact element={<KyRecommend />} />
                    <Route path="Board" exact element={<Board />} />
                    <Route
                        path="Board/PostBoard"
                        exact
                        element={<PostBoard />}
                    />
                    <Route
                        path="Board/EditBoard/:brdKey"
                        exact
                        element={<EditBoard />}
                    />
                    <Route path="Board/:brdKey" exact element={<ViewBoard />} />
                    <Route path="Feedback" exact element={<Feedback />} />
                    <Route path="*" element={<NotFound />} />
                </Routes>
                <Footer />
            </RecoilRoot>
        </React.Fragment>
    );
};
export default App;
