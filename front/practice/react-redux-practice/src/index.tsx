import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { createStore } from 'redux';
import counter from "./reducers";

const store = createStore(counter);

const render = () => ReactDOM.render(
    <React.StrictMode>
        <App
            value={store.getState()}
            onIncrement={() => store.dispatch({ type: "INCREMENT" })}
            onDecrement={() => store.dispatch({ type: "DECREMENT" })}
        />
    </React.StrictMode>,
    document.getElementById('root')
);

render();
store.subscribe(render);
