import _ from 'lodash'



stringAndNumber();
typeExample();


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


