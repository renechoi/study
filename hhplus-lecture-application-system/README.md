# 항해 플러스 [ 2주차 과제 ] 특강 신청 서비스

## 프로젝트 실행 전 데이터베이스 설정 방법

> Docker가 설치되어 있고 구동중이어야 합니다.

1. 프로젝트를 클론합니다.
    ```sh
    git clone <repository-url>
    cd hhplus-lecture-application-system
    ```

2. 프로젝트 루트 디렉토리에 `.env` 파일을 생성하고 다음 내용을 추가합니다:
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
