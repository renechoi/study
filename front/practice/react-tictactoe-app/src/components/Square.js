import React, {Component} from 'react';
import './Square.css';

const Square = ({onclick, value}) => {


    return (
        <button className="square"
                onClick={() => onclick()}>
            {value}
        </button>
    );
}

export default Square;