let boxEl = document.querySelector('.box');
console.log(boxEl);

boxEl.addEventListener();

boxEl.addEventListener(1,2);


// 이벤트 상황
boxEl.addEventListener('click', 2);

// 이벤트 상황 핸들러
boxEl.addEventListener('click', function(){
    console.log('click');
    boxEl.classList.add('active');
    console.log(boxEl.classList.contains('active'));

    boxEl.classList.remove('active');
});




boxEl.classList.add('active');
let isContains = boxEl.classList.contains('active');
console.log(isContains);


boxEl.classList.remove('active');
isContains=boxEl.classList.contains('active');




const boxEls = document.querySelectorAll('.box');
console.log(boxEls);

boxEls.forEach(function (boxEl, index){});

boxEls.forEach(function (boxEl, index){
    boxEl.classList.add(`order-${index +1}`);
    console.log(index, boxEl);
})



// getter
console.log(boxEl.textContent);

// setter
boxEl.textContent = 'h';




const a = 'hello';

// 메서드 체이닝
const b = a.split('').reverse().join('');



