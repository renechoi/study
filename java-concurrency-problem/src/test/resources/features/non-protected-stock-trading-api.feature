# non-protected-stock-trading-api.feature

  Feature: 동시성 방어 로직이 없는 경우의 테스트

    Scenario: 동시 매수 요청의 순차적 처리 - 제한 준수
      Given "normal" API를 호출
      Given 초기 주식 수가 10000인 포트폴리오가 주어졌을 때
      And 다음 스텝을 위해 1초간 딜레이
      And 제한 간격을 준수하여 500을 매수하려고 2회 시도한다
      When 포트폴리오를 조회하면
      And 포트폴리오에 주식 수는 11000으로 확인되어야 한다

    Scenario: 동시 매수 요청의 순차적 처리 - 제한 미준수
      Given "normal" API를 호출
      Given 초기 주식 수가 10000인 포트폴리오가 주어졌을 때
      And 다음 스텝을 위해 1초간 딜레이
      And 2개의 스레드가 동시에 500을 매수하려고 시도한다
      When 포트폴리오를 조회하면
      And 의도한 예외는 발생하지 않는다
      And 주식 수는 11000 혹은 10500으로 확인된다

