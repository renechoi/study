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

