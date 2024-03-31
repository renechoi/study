# identifier-stock-trading-api.feature

  Feature: lock free 기반 접근의 식별자 해결 방식 테스트

    Scenario: 동시 매수 요청의 순차적 처리
      Given "identifier" API를 호출
      Given 초기 주식 수가 10000인 포트폴리오가 주어졌을 때
      And 다음 스텝을 위해 1초간 딜레이
      And 2개의 스레드가 동시에 500을 매수하려고 시도한다
      When 포트폴리오를 조회하면
      Then 의도한 예외가 확인된다
      Then 포트폴리오에 주식 수는 10500으로 확인되어야 한다

