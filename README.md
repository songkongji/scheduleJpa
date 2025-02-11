# 일정관리
<br><br>

## API 명세서

### 일정 API

|API 기능|Method|URL|request|response|상태코드|
|------|------|------|------|------|------|
|일정 등록|POST|/schedules/save|요청 body|등록 정보|201: 정상 등록|
|일정 단건 조회|GET|/schedules/{id}|요청 param|단건 응답 정보|200: 정상 조회|
|일정 수정|PATCH|/schedules/{id}|요청 body|수정 정보|200: 정상 수정|
|일정 삭제|DELETE|/schedules/{id}|요청 param| - |200: 정상 삭제|

### 유저 API
|API 기능|Method|URL|request|response|상태코드|
|------|------|------|------|------|------|
|유저 등록|POST|/users/save|요청 body|등록 정보|201: 정상 등록|
|유저 단건 조회|GET|/users/{id}|요청 param|단건 응답 정보|200: 정상 조회|
|유저 수정|PATCH|/users/{id}|요청 body|수정 정보|200: 정상 수정|
|유저 삭제|DELETE|/users/{id}|요청 param| - |200: 정상 삭제|
|로그인|POST|/users/login|요청 form|로그인 정보|200: 정상 로그인|

### 댓글 API
| API 기능   | Method | URL                            |request| response |상태코드|
|----------|--------|--------------------------------|------|----------|------|
| 댓글 등록    | POST   | /comments/save                 |요청 body| 등록 정보    |201: 정상 등록|
| 댓글 목록 조회 | GET    | /comments/{id}                 |요청 param| 다건 응답 정보 |200: 정상 조회|
| 댓글 수정    | PUT    | /comments/{id}                 |요청 body| 수정 정보    |200: 정상 수정|
| 댓글 삭제    | DELETE | /comments/{id}?scheduleId={id} |요청 param| -        |200: 정상 삭제|

<br><br>
## ERD
![Image](https://github.com/user-attachments/assets/4af5a0e2-4959-4c65-b8e9-c362697e8eef)