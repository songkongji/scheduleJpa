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

## ERD
![Image](https://github.com/user-attachments/assets/9e32918e-8775-4308-8a4c-148286f350f7)