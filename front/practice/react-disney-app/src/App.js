import logo from './logo.svg';
import './App.css';
import Nav from "./components/Nav";
import styled from "styled-components";
import Banner from "./components/Banner";
import Category from "./components/Category";
import Row from "./components/Row";
import requests from "./api/request";

function App() {
    return (
        <Container>
            <Nav/>
            <Banner/>
            <Category/>
            <Row title="Trending Now" id="TN" fetchUrl={requests.fetchTrending} />
            <Row title="Top Rated" id="TR" fetchUrl={requests.fetchTrending} />
        </Container>
    );
}

export default App;

const Container = styled.main `
    position:relative;
  min-height: calc(100vh - 250px);
  overflow-x: hidden;
  display: block;
  top: 72px;
  padding: 0 calc(.3vw + 5px);
  
  &:after{
    background: url("/images/home-background.png") center center / cover no-repeat fixed;
    content: "";
    position: absolute;
    inset: 0;
    opacity: 1;
    z-index: -1;
    
  }
  
`