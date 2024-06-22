# 항해 플러스 [ 2주차 과제 ] 특강 신청 서비스

## 요구 사항 

```
💡 아래 명세를 잘 읽어보고, 서버를 구현합니다.

📌 Description

- `특강 신청 서비스`를 구현해 봅니다.
- 항해 플러스 토요일 특강을 신청할 수 있는 서비스를 개발합니다.
- 특강 신청 및 신청자 목록 관리를 RDBMS를 이용해 관리할 방법을 고민합니다.

📌 Requirements

- 아래 2가지 API 를 구현합니다.
   - 특강 신청 API
   - 특강 신청 여부 조회 API
- 각 기능 및 제약 사항에 대해 단위 테스트를 반드시 하나 이상 작성하도록 합니다.
- 다수의 인스턴스로 어플리케이션이 동작하더라도 기능에 문제가 없도록 작성하도록 합니다.
- 동시성 이슈를 고려하여 구현합니다.

📌 API Specs

1️⃣(핵심) 특강 신청 API POST /lectures/apply

- 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
- 동일한 신청자는 한 번의 수강 신청만 성공할 수 있습니다.
- 특강은 `4월 20일 토요일 1시` 에 열리며, 선착순 30명만 신청 가능합니다.
- 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
- 어떤 유저가 특강을 신청했는지 히스토리를 저장해야한다.

2️⃣(기본) 특강 신청 완료 여부 조회 API GET /lectures/application/{userId}

- 특정 userId 로 특강 신청 완료 여부를 조회하는 API 를 작성합니다.
- 특강 신청에 성공한 사용자는 성공했음을, 특강 등록자 명단에 없는 사용자는 실패했음을 반환합니다. (true, false)


📌 심화 과제

3️⃣(필수) 특강 선택 API GET /lectures

- 단 한번의 특강을 위한 것이 아닌 날짜별로 특강이 존재할 수 있는 범용적인 서비스로 변화시켜 봅니다.
- 이를 수용하기 위해, 특강 엔티티의 경우 기존의 설계에서 변경되어야 합니다.
- 특강의 정원은 30명으로 고정이며, 사용자는 각 특강에 신청하기전 목록을 조회해볼 수 있어야 합니다.
   - 추가로 정원이 특강마다 다르다면 어떻게 처리할것인가..? 고민해 보셔라~

💡 KEY POINT

- 정확하게 30명의 사용자에게만 특강을 제공할 방법을 고민해 봅니다.
- 같은 사용자에게 여러 번의 특강 슬롯이 제공되지 않도록 제한할 방법을 고민해 봅니다.
```


## 구상

### 총괄 시나리오

1. 사용자는 특강 신청 페이지에 접속한다.
2. 클라이언트는 서버에서 현재 특강 목록을 조회한다.
3. 서버는 특정 시간 전에 특강 신청을 막는다.
4. 클라이언트는 특정 시간이 되면 특강 신청 버튼을 활성화한다.
5. 사용자가 특강 신청 버튼을 클릭하면 클라이언트는 서버에 신청 요청을 보낸다.
6. 서버는 신청 요청을 처리하고, 결과를 클라이언트에 응답한다.

### 세부 시나리오

#### 1. 특강 신청 페이지 접속
- 사용자는 특강 신청 웹페이지에 접속하여 현재 제공되는 특강 목록을 확인한다.
- 클라이언트는 서버로부터 특강 목록을 가져와 사용자에게 표시한다.

#### 2. 특강 목록 조회
- 클라이언트는 서버에 `/lectures` API를 호출하여 현재 제공되는 모든 특강 목록을 조회한다.
- 서버는 DB에서 모든 특강 정보를 조회하여 클라이언트에 반환한다.
- 클라이언트는 반환된 특강 목록을 사용자에게 표시한다.

#### 3. 특강 신청 시간 제한
- 서버는 특강의 시작 시간(`4월 20일 토요일 1시`) 전에 특강 신청을 막는다.
- 클라이언트는 특강 시작 시간 전까지 신청 버튼을 비활성화 상태로 유지한다.
- 클라이언트는 특강 시작 시간이 되면 신청 버튼을 활성화한다.

#### 4. 특강 신청
- 특강 신청 시간이 되면 사용자는 신청 버튼을 클릭할 수 있다.
- 클라이언트는 사용자가 신청 버튼을 클릭할 때, `/lectures/apply` API를 호출하여 신청 요청을 보낸다.
- 서버는 신청 요청을 처리하여 다음과 같은 로직을 수행한다:
   - 신청자가 30명을 초과하는지 확인한다.
   - 동일한 사용자가 이미 신청했는지 확인한다.
   - 신청이 가능하면 신청 정보를 DB에 저장하고, 현재 참가자 수를 업데이트한다.
   - 신청이 완료되면 클라이언트에 성공 응답을 반환한다.
   - 신청자가 30명을 초과하거나 이미 신청한 사용자인 경우, 클라이언트에 실패 응답을 반환한다.

#### 5. 신청 결과 응답
- 클라이언트는 서버로부터 신청 결과 응답을 받아 사용자에게 표시한다.
- 성공적으로 신청된 경우, 사용자는 성공 메시지를 확인하고 신청 목록이 갱신된다.
- 신청이 실패한 경우, 사용자는 실패 이유를 확인한다.

#### 6. 범용적인 특강 서비스
- 사용자는 특정 날짜에 열리는 여러 특강 목록을 조회할 수 있다.
- 클라이언트는 서버에 `/lectures?date={date}` 와 같은 검색 파라미터가 추가된 API를 호출하여 특정 날짜에 열리는 특강 목록을 조회한다.
- 서버는 해당 날짜에 열리는 모든 특강 정보를 DB에서 조회하여 클라이언트에 반환한다.
- 클라이언트는 반환된 특강 목록을 사용자에게 표시한다.
- 각 특강의 정원이 30명으로 고정되어 있지만, 특정 특강은 다른 정원을 가질 수 있다.
- 특강의 정원이 다를 경우, 서버는 각 특강별로 최대 정원 수를 저장하고 관리한다.
- 클라이언트는 각 특강의 현재 신청자 수와 최대 정원 수를 사용자에게 표시한다.








## 프로젝트 실행 전 데이터베이스 설정 방법

> Docker가 설치되어 있고 구동중이어야 합니다.

1. 프로젝트를 클론합니다.
    ```sh
    git clone <repository-url>
    cd hhplus-lecture-application-system
    ```

2. 프로젝트 루트 디렉토리에 `.env` 파일을 생성하고 다음 내용을 추가합니다.
    ```env
    SPRING_DATASOURCE_USERNAME=root
    SPRING_DATASOURCE_PASSWORD=1234
    ```

   필요한 경우, 데이터베이스 사용자 이름과 비밀번호를 자신의 환경에 맞게 변경합니다.

3. Docker Compose를 사용하여 데이터베이스를 시작합니다.
    ```sh
    docker-compose up -d
    ```

4. 데이터베이스가 설정되고 컨테이너가 실행 중인지 확인합니다.
    ```sh
    docker ps
    ```

5. Spring Boot 애플리케이션을 실행합니다.
    ```sh
    ./mvnw spring-boot:run
    ```
6. 정상 작동 후 확인

도커가 정상적으로 실행되면 `localhost:3301` 포트와 `3302` 포트로 `Database` 조회시 다음과 같이 확인되어야 합니다. 

![docker-after-database.png](document%2Fdocker-after-database.png)
