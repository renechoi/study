console.log(window.innerHeight);
console.log(window.innerWidth);


console.log(window.scrollX, window.scrollY);

setTimeout(() => {
    window.scrollTo(0, 500)
}, 1000);


setTimeout(() => {
    window.scrollTo({
        left: 0,
        top: 500,
        behavior: 'smooth'
    })
}, 1000);

const parent = document.querySelector('.parent');
const child = document.querySelector('.child');

console.log(parent.offsetLeft, parent.offsetTop);

console.log(parent.getBoundingClientRect());
console.log(child.getBoundingClientRect());

