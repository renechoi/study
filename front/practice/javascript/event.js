const parentEl = document.querySelector(".parent");
const childEl = document.querySelector('.child');

parentEl.addEventListener('click', ()=>{
    console.log('parent');
})

childEl.addEventListener('click', ()=>{
    console.log('child');
})



parentEl.addEventListener('click', event=>{
    console.log(event);
    console.log(event.currentTarget);
})


parentEl.addEventListener('wheel', event=>{
    console.log(event);
    console.log(event.currentTarget);
})



const inputEl = document.querySelector('input');
inputEl.addEventListener('keydown', event => {
    console.log(event.key);
})


// 기본 동작 방지
parentEl.addEventListener('wheel', event =>{
    event.preventDefault();
})


const anchorEl = document.querySelector('a');
anchorEl.addEventListener('click', event =>{
    event.preventDefault();
})



// 버블링과 캡처링

window.addEventListener('click', event =>{
    console.log('window');
})

document.body.addEventListener('click', event =>{
    console.log('body');
}, {capture:true})

parentEl.addEventListener('click', event =>{
    console.log('parent')
    event.stopPropagation();
})


childEl.addEventListener('click', event =>{
    console.log('child')
})





const handler = () => {
     console.log('parent!')
}


parentEl.addEventListener('click', event => {
    console.log('parent!')
}, {
    once: true
})


// 기본 동작과 핸들러 실행 분리

parentEl.addEventListener('wheel', ()=>{
    for (let i =0;i<10000; i+=1){
        console.log(i)
    }
}, {
    passive:true
})



// 이벤트 위임

const childEls = document.querySelectorAll('.child');

childEls.forEach(el => {
    el.addEventListener('click', event =>{
        console.log(event.target.textContent);
    })
})


parentEl.addEventListener('click', event => {
    const childEl = event.target.closest('.child')
    if (childEl){
        console.log(childEl.textContent);
    }
})


// 마우스와 포인터 이벤트

childEl.addEventListener('click', () => {
    childEl.classList.toggle('active');
})

childEl.addEventListener('dblclick', () => {
    childEl.classList.toggle('active');
})

childEl.addEventListener('mousedown', () => {
    childEl.classList.add('active');
})


childEl.addEventListener('mouseup', () => {
    childEl.classList.remove('active');
})




childEl.addEventListener('mouseenter', () => {
    childEl.classList.add('active');
})


childEl.addEventListener('mousemove', () => {
    childEl.classList.remove('active');
})




childEl.addEventListener('contextmenu', event => {
    console.log(event)
})


// keyboard event

const inputEl = document.querySelector('input');
inputEl.addEventListener('keydown', event => {
    console.log(event.key);
    if (event.key === 'Enter'){
        console.log(event.isComposing)
        console.log(event.target.value)
    }

    if (event.key === 'Enter' && !event.isComposing){
        console.log(event.target.value)
    }
})





// form and focus



const formEl = document.querySelector('form')
const inputEls = document.querySelectorAll('input')

inputEls.forEach(el =>{
    el.addEventListener('focus', ()=>{
        formEl.classList.add('active');
    })

    el.addEventListener('blur', ()=>{
        formEl.classList.remove('active')
    })

    el.addEventListener('change', evnet =>{

    })
});

formEl.addEventListener('submit', event => {
    event.preventDefault();

    const data = {
        id : event.target[0].value,
        pw : event.target[1].value
    }

    console.log(data);
})





let child1 = document.querySelector('.child:nth-child(1)');
let child2 = document.querySelector('.child:nth-child(2)');

// 강제로 이벤트 발생시킴
child1.addEventListener('click', event => {
    child2.dispatchEvent(new Event('click'))
    child2.dispatchEvent(new Event('wheel'))
    child2.dispatchEvent(new Event('keydown'))
})


child1.addEventListener('hello', event =>{
    console.log('custom');
    console.log(event.detail)
})

child2.addEventListener('click', () => {
    child1.dispatchEvent(new Event('hello'))
})





