



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

function showSearch() {
    headerEl.classList.add('searching')
    stopScroll()
    headerMenuEls.reverse().forEach((el, index) => {
        el.style.transitionDelay = `${index * duration / headerMenuEls.length}s` // 순서 * 지연 시간 / 애니메이션할 요소 개수
    })
    // .reverse() 사용하지 않고 원래 순서대로 반복 처리.
    searchDelayEls.forEach((el, index) => {
        el.style.transitionDelay = `${index * duration / searchDelayEls.length}s`
    })
    // 검색 인풋 요소가 나타난 후 동작!
    setTimeout(() => {
        searchInputEl.focus()
    }, 600);
}
function hideSearch() {
    headerEl.classList.remove('searching')
    playScroll()
    headerMenuEls.reverse().forEach((el, index) => {
        el.style.transitionDelay = `${index * duration / headerMenuEls.length}s`
    })
    searchDelayEls.reverse().forEach((el, index) => {
        el.style.transitionDelay = `${index * duration / searchDelayEls.length}s`
    })
    searchDelayEls.reverse() // 나타날 때 원래의 순서대로 처리해야 하기 때문에 다시 뒤집어서 순서 돌려놓기!
    searchInputEl.value = '' // 입력값 초기화
}
function playScroll() {
    // documentElement is <html>
    document.documentElement.classList.remove('fixed')
}
function stopScroll() {
    document.documentElement.classList.add('fixed')
}


// 요소의 가시성 관찰 로직!
const io = new IntersectionObserver(entries => {
    // entries는 `io.observe(el)`로 등록된 모든 관찰 대상 배열.
    entries.forEach(entry => {
        // 사라질 때.
        if (!entry.isIntersecting) {
            return
        }
        entry.target.classList.add('show')
    })
})
// 관찰할 요소들 검색
const infoEls = document.querySelectorAll('.info')
// 관찰 시작!
infoEls.forEach(el => io.observe(el))