import React from 'react';
import { Route, Routes, Outlet } from 'react-router-dom';
import VideoPage from './pages/VideoPage/VideoPage';
import NavigationBar from "./components/NavigationBar/navigationBar.jsx";
import SideBar from "./components/SideBar/sideBar.jsx";
import MainPage from "./pages/MainPage/mainPage.jsx";

const Layout = () => {
    return (
        <>
            <NavigationBar />
            <SideBar />
            <main>
                <Outlet />
            </main>
        </>
    );
};

const App = () => {
    return (
        <React.Fragment>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<MainPage />} />
                    <Route path='/results/:input' element={<SearchedVideosPage />} />
                    <Route path='/video/:videoId' element={<VideoPage />} />
                </Route>
            </Routes>
        </React.Fragment >
    );
}

export default App;
