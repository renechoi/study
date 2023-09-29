//domain: 유효 도메인 설정
// path: 유효 경로 설정
// expires: 만료 날짜 설정
// max-age: 만료 타이머 설정

document.cookie = 'a=1'
document.cookie = 'a=22'
console.log(document.cookie);


document.cookie = 'l=1; domain=localhost'
document.cookie = 'p=1; domain=localhost; path=/'
document.cookie = `c=1; max-age=${60*60*24*3}`
document.cookie = `d=1; expires=${new Date(2022, 11, 16).toUTCString()}`



// storage
// 도메인 단위로 저장
// 5MB 저장
// 세션 혹은 영구 저장 가능
// sessionStorage -> 브라우저 세션이 유지되는 동안에만 데이터 저장
// localStorage -> 따로 제거하지 않으면 영구적으로 데이터 저장


localStorage.setItem('a', 'hello')
localStorage.setItem('b', JSON.stringify({x: 1, y:2}));



console.log(JSON.parse(localStorage.getItem('a')))

localStorage.clear();


// 세션도 동일





