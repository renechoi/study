import {Component} from "../core/heropy";
import messageStore from '../store/message'


export  default class Title extends Component{
    constructor() {
        super({
            tageName:'h1'
        });
        messageStore.subscribe('message', ()=>{
            this.render()
        })
    }

    render() {
        this.el.textContent = `title: ${messageStore.state.message}`
    }
}