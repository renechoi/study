



// 장바구니!
// 장바구니 관련 요소 찾기.
const basketStarterEl = document.querySelector('header .basket-starter')
const basketEl = basketStarterEl.querySelector('.basket')

basketStarterEl.addEventListener('click', event => {
    event.stopPropagation() // 이벤트 버블링 정지! - 버튼을 클릭했을 때 드롭다운 메뉴가 나타나야 함.
    if (basketEl.classList.contains('show')) {
        hideBasket()
    } else {
        showBasket()
    }
})
basketEl.addEventListener('click', event => {
    event.stopPropagation() // 이벤트 버블링 정지! - 드롭다운 메뉴 영역을 클릭했을 때 메뉴가 사라지지 않아야 함.
})
// 화면 전체를 클릭했을 때 메뉴가 사라짐.
window.addEventListener('click', () => {
    hideBasket()
})

// 특정 로직을 직관적인 함수 이름으로 묶음.
function showBasket() {
    basketEl.classList.add('show')
}
function hideBasket() {
    basketEl.classList.remove('show')
}


// 검색 ! 
// 헤더 검색 관련 요소 찾기.
const headerEl = document.querySelector('header')
const headerMenuEls = [...headerEl.querySelectorAll('ul.menu > li')]
const searchWrapEl = headerEl.querySelector('.search-wrap')
const searchStarterEl = headerEl.querySelector('.search-starter')
const searchCloserEl = searchWrapEl.querySelector('.search-closer')
const searchShadowEl = searchWrapEl.querySelector('.shadow')
const searchInputEl = searchWrapEl.querySelector('input')
const searchDelayEls = [...searchWrapEl.querySelectorAll('li')]
const duration = .4 // 초(seconds) 단위, 시간을 변수에 저장해서 사용하면 쉽게 관리 용이


searchStarterEl.addEventListener('click', showSearch)
searchCloserEl.addEventListener('click', event => {
    event.stopPropagation() // 데스크탑 레이아웃에서 클릭 이벤트가 버블링되어, 모바일 레이아웃에서 searchTextFieldEl가 클릭된 상태로 변하는 것을 방지
    hideSearch()
})
searchShadowEl.addEventListener('click', hideSearch)

function showSearch(){
    headerEl.classList.add('searching');
    document.documentElement.classList.add('fixed');
}

function hideSearch(){
    headerEl.classList.remove('searching');
    document.documentElement.classList.remove('fixed');
}