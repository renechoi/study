// export default class App{
//     constructor() {
//         this.el =document.createElement('div');
//         this.el.textContent = 'hello, world!';
//     }
// }

import { Component} from "./core/heropy";

export default class App extends Component{
    // 생략 가능
    // constructor() {
    //     super();
    // }
    render() {
        this.el.textContent = 'hello, world';
    }
}