import logo from './logo.svg';
import './App.css';
import Nav from "./components/Nav";
import styled from "styled-components";

function App() {
    return (
        <Container>
            <Nav/>
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