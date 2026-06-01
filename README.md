# EnjoyTrip History

역사 관광지와 한능검 퀴즈를 연결하는 교육형 여행 서비스입니다.

공공 유적지 데이터를 기반으로 지역별 역사 장소를 조회하고, 장소와 관련된 시대, 인물, 사건, 키워드를 한능검 문제와 연결해 학습형 여행 경험을 제공하는 것을 목표로 합니다.

## 주요 기능

- 지역별 역사 관광지 조회
- 역사 태그 조회
  - 시대
  - 사건
  - 인물
  - 장소 유형
  - 한능검 키워드
- 지역별 역사 태그 조회
- 한능검 퀴즈 이미지 조회
- 퀴즈와 역사 태그 연결 조회
- Hotplace, 게시판, 공지사항 조회 API

## 기술 스택

- Java 17
- Spring Boot 4
- Spring MVC
- Spring Security
- MyBatis
- MySQL
- Springdoc OpenAPI / Swagger

## 데이터 구조

핵심 테이블은 다음과 같습니다.

- `regions`: 시도/구군 지역 정보
- `historical_places`: 역사 관광지 정보
- `history_tags`: 시대, 사건, 인물, 키워드 태그
- `place_tags`: 장소와 역사 태그 연결
- `quizzes`: 한능검 문제 이미지 기반 퀴즈
- `quiz_tags`: 퀴즈와 역사 태그 연결
- `region_history_tags`: 지역과 역사 태그 연결
- `hotplaces`: 사용자 등록 역사 명소
- `boards`: 여행 후기 및 코스 공유 게시판
- `notices`: 공지사항

## 실행 방법

백엔드 프로젝트로 이동합니다.

```bash
cd EnjoyHistoryTrip
```

애플리케이션을 실행합니다.

```bash
./mvnw spring-boot:run
```

Windows 환경에서는 다음 명령을 사용할 수 있습니다.

```bash
mvnw.cmd spring-boot:run
```

## DB 설정

기본 DB 설정은 다음 파일에 있습니다.

```text
EnjoyHistoryTrip/src/main/resources/application.properties
```

현재 기본 설정:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/history_trip?serverTimezone=Asia/Seoul
spring.datasource.username=ssafy
spring.datasource.password=ssafy
```

## Swagger

서버 실행 후 Swagger UI에서 API를 확인할 수 있습니다.

```text
http://localhost:8080/swagger-ui.html
```

주요 조회 API:

- `GET /api/overview/counts`
- `GET /api/regions`
- `GET /api/regions/{regionId}/history-tags`
- `GET /api/history-tags`
- `GET /api/places`
- `GET /api/places/{placeId}/tags`
- `GET /api/quizzes`
- `GET /api/quizzes/{quizId}/tags`
- `GET /api/region-history-tags`
- `GET /api/hotplaces`
- `GET /api/boards`
- `GET /api/notices`

## 현재 구현 범위

현재는 DB에 적재된 데이터를 Swagger로 확인하기 위한 조회 API 중심으로 구현되어 있습니다.

한능검 퀴즈는 별도 선지 테이블을 사용하지 않고, 선지까지 포함된 문제 이미지 자체를 제공하는 방식으로 설계되어 있습니다.

## 향후 계획

- 역사 관광지 데이터 적재 및 상세 조회 고도화
- 퀴즈 풀이 기능 구현
- 여행 계획 생성 및 경로 저장
- Hotplace 등록/수정/삭제
- 게시판 및 공지사항 CRUD
- 프론트엔드 연동
