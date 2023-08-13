let myName = "Name";
let email = 'email@email.com';
let hello = `Hello ${myName}?!`;


let user = {
    name: 'Name',
    age: 85
}


let undef;

let empty = null;


function hello(){
    console.log("이름이 있는 함수");
}

let world = function (){
    console.log("이름이 없는 함수 = 익명 함수");
}



const heropy = {
    name:'H',
    age: 85,
    getName: function (){
        return this.name;
    }
};

const hisName = heropy.getName();



