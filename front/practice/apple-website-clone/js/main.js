



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