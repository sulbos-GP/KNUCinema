# 🎬 영화 예매 시스템 - Web Server Framework Final Project

## 👥 팀원
- 201804072 이근주  
- 201804127 홍성현  
- 201904110 지승현  

---

## 📌 프로젝트 개요

영화 예매 시스템을 기반으로 한 MVC 웹 프레임워크 구조의 웹 애플리케이션을 설계 및 구현하였습니다.

---

## 🧱 구조 및 구성

### 1. DTO 설계

| DTO | 필드 설명 |
|---|---|
| `CinemaDTO` | `cid`, `time`, `seat` |
| `MovieDTO` | `id`, `title`, `image`, `content` |
| `ReservationDTO` | `id`, `cinemaDTO`, `userId`, `reserSeat` |
| `UserDTO` | `id`, `name`, `age`, `phoneNumber` |

- `CinemaListDTO`는 `MovieDTO`를 상속하고, `List<Time>`을 포함함.

---

### 2. 데이터베이스 설계

- `DatabaseDAO`: 인터페이스, 데이터의 get/set 정의.
- `DatabaseImple`: 생성자에서 데이터 초기화, set/get 구현.
- `UserDTO` 및 `ReservationDTO`: 자동 ID 생성 로직 포함.

---

### 3. 예약 기능

- 예매 흐름:
  - `/userForm` → `/reserve` → `/seat` → `/payment`
  - 필수 데이터: 예약자 ID, 영화 시간, 영화 ID

---

### 4. 예약 조회

- `/checkbook` 요청:
  - 성공 시: `/mybook`
  - 실패 시: `/mybook` (에러 처리 포함)

---

### 5. GPT 기능 연동

- `/question` 요청
- 컨트롤러에서 GPT 기능 요청 → Prompt 및 설정 변수 저장
- 응답 데이터 파싱 후 View에 출력
- `HttpEntity`를 활용한 DTO 기반 요청
- 응답 DTO(`ResponseDTO`) 저장 및 전달 처리

---

### 6. 프론트 및 오류 처리

- 500 에러: 커스텀 오류 페이지 처리
- 404 에러: Not Found 페이지 처리

---

## 🛠️ 기술 스택

- Java 기반 MVC 프레임워크
- DTO/DAO 구조
- GPT 연동 API 사용
- 기본적인 HTML 뷰 처리

---

## 🙏 감사합니다
"""

