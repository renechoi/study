import React, {Component} from 'react';
import './Square.css';

class Square extends Component {



    render() {
        return (
            <button className="square"
                    onClick={() => this.props.onclick()}>
                {this.props.value}
            </button>
        );
    }
}

export default Square;