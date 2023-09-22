import _ from 'lodash'



stringAndNumber();
typeExample();

boolean();


function stringAndNumber() {
    console.log(_.upperCase("hello"))


    const String1 = "Hello"
    const String2 = 'hello'
    const String3 = `hello`


    const number = -123.1234

    console.log(typeof (number + undefined))


    const a = 0.1
    const b = 0.2

    console.log((a + b).toFixed(1))
}


function typeExample() {
    let age = null
    console.log(age)

    setTimeout(function () {
        age = 85
        console.log(age)
    }, 1000)


    const user = {
        name: 'He',
        age: 85
    }
}


console.log(Object.prototype.toString.call(null).slice(8 ,-1));



function checkType(data){
    return Object.prototype.toString.call(data).slice(8, -1);
}


function boolean() {
// and 연산자  -> 참이면 다음으로 넘어가고 false 인 경우 출력 -> or 연산자의 경우 반대
    console.log(true && false); // false
    console.log(1 && 0); // 0
    console.log(1 && 2 && 0) // 0
    console.log(1 && 0 && 2) // 0
}


function nullish() {
    const n = 0
    const num1 = n || 7
    console.log(num1); // 7

    const num2 = n ?? 7
    console.log(num2) // 0


    console.log(null ?? 1); //1
    console.log(undefined ?? 2) // 2
    console.log(null ?? undefined)  // undefined
}

nullish();

function ternary() {
    const a = 1

    if (a < 2) {
        console.log("참");
    } else {
        console.log('거짓');
    }


    console.log(a < 2 ? 'true' : 'false')
}

ternary();


function spreadOperator() {
    const a = [1, 2, 3]
    console.log(...a) // 1, 22, 3

    const b = [4, 5, 6]
    const c = a.concat(b);
    console.log(c);

    const d = [a, b]
    console.log(d) // [[1,2,3][4,5,6]]

    const e = [...a, ...b]
    console.log(e) // [1,2,3,4,5,6]


    const aa = {x: 1, y: 2}
    const bb = {y: 3, z: 4}
    const cc = Object.assign({}, a, b)
    const dd = {...aa, ...bb}
    console.log(dd) // {x:1, y:3, z:4}


    function fn(x,y,z){
        console.log(x, y, z);
    }

    fn(1,2,3)
    const aaa = [1,2,3];
    fn(...a); // 1,2,3


}

spreadOperator();


function destructuringAssignment() {
    const arr = [1, 2, 3]
    const a = arr[0]
    const b = arr[1]
    const c = arr[2]


    const [aa, bb, cc] = arr
    console.log(aa, bb, cc)

    const [aaa, ...rest] = arr
    console.log(a, rest) // 1, [2,3]


    const obj = {
        aaaa:1,
        bbbb:2,
        cccc:3
    }

    const {cccc = 5} = obj
    console.log(cccc) // 3
}

destructuringAssignment();

function optionalChaining() {
    const user = {}
    console.log(user.name)

    const user1 = null
    console.log(user1?.name)

    const userA = {
        name:'HEROPY',
        age:85,
        address: {
            country: 'Korea',
            city: 'Seoul'
        }
    }

    const userB = {
        name: 'neo',
        age:22
    }

    function getCity(user){
        return user.address?.city || '주소 없음.'
    }

    console.log(getCity(userA)) //Seoul
    console.log(getCity(userB)) // 주소 없음
}

optionalChaining();


function forIteration() {
    const users = [
        {
            name: 'he',
            age: 30
        }
    ]

    for (user of users) {
        console.log(user.name)
    }


    for (const key in user) {
        console.log(key)
        console.log(user[key])
    }
}

forIteration();


function whileIteration() {
    let n = 0;
    while (n < 4) {
        console.log(n);
        n++;
    }
}

whileIteration();

function functionPractice() {
// 함수 선언문 -> 호이스팅 -> 유효한 범위 내에서 제일 꼭대기에 올라가서 존재한다.
    function hello() {
        console.log('hello')
    }


    hello2(); // 함수 표현식 -> 호이스팅이 안 되므로 에러가 난다
    const hello2 = function () {
        console.log("hello2")
    }


    console.log(hello2)

    function sum(a,b){
        return a + b;
    }

    function sum2(a, b=1){
        return a + b;
    }

    console.log(sum2(7))



    const user = {
        name: 'abc',
        age: 10
    }


    function getName(user){
        const {name} = user
        return name;
    }

    // 바로 구조분해해서 함수 내부로 할당한다
    function getName2({name}){
        return name;
    }

    function getEmail({email = '이메일이 없습니다.'}){
        return email
    }


    const fruits = ['apple', 'banana', 'cherry']
    function getSecondItem(array){
        return array[1];
    }

    function getSecondItem2([a, b, c]){
        return b
    }

    function getSecondItem3([, b]){
        return b
    }





    function sum(...rest){
        console.log(arguments)
        return rest.reduce(function (acc, cur){
            return acc + cur
        }, 0)
    }

    console.log(sum(1,2));
    console.log(sum(1,2,3,4));
    console.log(sum(1,2,3,4,5));



    function summation(a,b){
        return a + b
    }

    const summation2 = (a, b ) => a+ b


    const arrow = () => {}
    const arrow2 = x => {}
    const arrow3 = (x,y) => {}
    const arrow4 = x => {return x * x}
    const arrow5 = x => {return {a:1}};



    (() => {console.log(a*2)})();

    ( ()=>{}  ) ();
    ( function (){}  ) ();
    ((function (){} ));
    !function (){} ();
    +function (){}();


    // 콜백 -> 함수가 실행될 때 다른 함수를 매개 변수로 넣어준다.

    const a = callback => {
        console.log('a')
        callback()
    }

    const b = () => {
        console.log('b')
    }

    a(b);



    const sum1 = (a, b) => a + b;

    console.log(sum1(1,2));

    const sum3 = (a, b, c)=> {
        setTimeout( ()=>{
            c(a + b)
        }, 1000 )
    }


    sum3(2,3, (value)=> {console.log(value)})
    console.log();

    // 콜백 예제

    // https://www.gstatic.com/webp/gallery/4.jpg

    const loadImage = (url, cb => {
        const imgEl = document.createElement('img');
        imgEl.src = url;
        imgEl.addEventListener('load', ()=> {
            setTimeout( () => {
                cb(imgEl);
            },1000)
        })


    })

    const containerEl = document.querySelector('.container')
    loadImage('https://www.gstatic.com/webp/gallery/4.jpg', (imgEl)=>{
        containerEl.innerHTML=''
        containerEl.append(imgEl);
    });



    // 재귀

    let i = 0;
    const recur = () => {
        i +=1
        if (i<4){
            recur();
        }
    }


    const userA = {name:'a', parent : null};
    const userB = {name:'a', parent : userA};
    const userC = {name:'a', parent : userB};
    const userD = {name:'a', parent : userC};


    const getRootUser = user =>{
        if (user.parent){
            return getRootUser(user.parent)
        }
        return user
    }




    setTimeout( ()=>{
        console.log('hello')
    }, 2000)


    const scheduling = () =>{
        console.log('hello')
    }

    const timeout = setTimeout(scheduling, 2000);

    clearTimeout(timeout)


    setInterval(scheduling, 2000)



    // this
    // 일반 함수의 this는 호출 위치에서 정의
    // 화살표 함수의 this는 자신이 선언된 함수(렉시컬) 범위에서 정의

    function thisuser1(){
        this.firstName = 'abc'
        this.lastName = 'ef'
        return {
            firstName:'ab',
            lastName:'park',
            age:10,
            getFullName: function ()  {
                return `${this.firstName} ${this.lastName}`
            }
        }
    }

    function thisuser2(){
        this.firstName = 'abc'
        this.lastName = 'ef'
        return {
            firstName:'ab',
            lastName:'park',
            age:10,
            getFullName: () => {
                return `${this.firstName} ${this.lastName}`
            }
        }
    }

    console.log(thisuser1().getFullName())


    const lewis = {
        firstName: 'lew',
        lastName: 'lee'
    }

    const u = thisuser1()
    console.log(u.getFullName().call(lewis))



    const timer = {
        title: 'timer',
        timeout(){
            console.log(this.title)
            setTimeout( ()=> {
                console.log(this.title)
            }, 1000)
        }
    }
}

functionPractice();


function classPractice() {
    const fruits = new Array('apple', 'banana', 'cherry');


    Array.prototype.heropy = function(){
        console.log(this)
    }

    fruits.heropy()

    const arr = []
    arr.heropy()


    function User(first, last){
        this.firstName = first
        this.lastName = last
    }

    const heropy = new User('h', 'park')
    const neo = new User('neo', 'lee')

    User.prototype.getFullname = function  (){
        return `${this.firstName} ${this.lastName}`
    }


    console.log(heropy);
    console.log(neo);
    console.log(heropy.getFullname());

    class User2 {
        constructor(first, last) {
            this.firstName = first
            this.lastName = last
        }

        getFullName(){
            return `${this.firstName} ${this.lastName}`
        }


        get fullName(){
            return `${this.firstName} ${this.lastName}`
        }

        static isUser(user){
            if (user.firstName && user.lastName){
                return true;
            }
            return false;
        }
    }


    class Vehicle{
        constructor(price, accelration) {
        }
    }

    class Bicycle extends Vehicle{
        constructor(price=100, accelration) {
            super(price);
        }
    }

    class A {
        constructor() {
        }
    }

    class B extends A{
        constructor() {
            super();
        }
    }

    class C extends B{
        constructor() {
            super();
        }
    }

    const a = new A()
    const b = new B()
    const c = new C()

    console.log(c instanceof A) // true
    console.log(c instanceof B) // true
    console.log(c instanceof C) // true



}

classPractice();

function stringPractice() {
    const str = '1234567';
    console.log(str.padEnd(10, '0'));
    console.log(str)

    str.slice(0,5);

    const str2 = 'apple, banana, cherry';
    str.split(', ');
}

stringPractice();

function numberAndMathPractice() {
    const num = 3.1415926535
    parseFloat(num.toFixed(2));

    const num2 = 1000000
    console.log(`${num.toLocaleString()}원`)


    Math.abs(-2);


    Math.max(1, 22, 38, 192);


    function random(min =0, max = 10){
        return Math.floor(Math.random() * (max-min)) + min
    }

    let randomN = random(101, 999);



}

numberAndMathPractice();


function datePractice() {
    let date = new Date();


    new Date(2022,11,16,12,57,30);


    let day = date.getDay();

    Date.prototype.isAfter = function (date){
        const a = this.getTime();
        const b = this.getTime();
        return a > b
    }
}

datePractice();


function arrayPractice() {
    const arr = ['a', 'b', 'c']
    const arr2 = ['d', 'e', 'f']

    arr[0];
    arr.at(0)

    arr.length-1

    let concat = arr.concat(arr2);



    const isValid = arr.every(item => item < 5);


    let filter = arr.filter(number => number < 30);


    const numbers = [ 5, 6,10, 130, 5, 4];
    numbers.find(item => item > 10);



    const arr3 = [1,2, [3,4, [5,6, [7,8]]]]
    arr.flat(3);
    arr.flat(Infinity)


    const users = [
        {name:'neo', age:85},
        {name:'amy', age:25}
    ]

    console.log(users.includes({name:'neo', age:85}))

    const newUsers = users.map(user => {
        return {
            ...user,
            isValid:true,
            email:null
        }
    })


    const num4= [1,2,3]
    const sum = num4.reduce((accumulator, currentValue)=>{
        return accumulator + currentValue;
    },0)



    arr.shift();


    arr.slice(0,3);


    arr.some(item => item > 3);


    const arr5 = ['a', 'b', 'c']
    arr.splice(2, 0, 'x')


}

arrayPractice();



