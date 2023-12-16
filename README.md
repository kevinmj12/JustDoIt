# JustDoIt
소프트웨어아키텍처 프로젝트

## 소개
JUST DO IT은 바쁜 현대인들을 위해 개발된 To-Do 및 Daily To-Do 관리 모바일 애플리케이션입니다. 이 앱은 사용자가 일상의 다양한 할 일을 효과적으로 관리하고 일일 목표를 달성하는 데 도움을 줍니다.

## 주요 기능 
- To-Do list: 할 일 목록을 만들고 관리할 수 있습니다. 각 To-Do에는 제목, 마감일, 목표 진행 상황 등을 기록할 수 있습니다.
- Daily To-Do: 매일 반복되는 목표를 설정하고 실행할 수 있습니다. 예를 들어, 매일 운동하기, 영어 공부하기, 물 2L 마시기 등의 목표를 설정하고 관리할 수 있습니다.
- 알림 서비스: 마감일이 다가오면 사용자에게 알림을 보내줍니다.

## 실행 화면 
![login](https://private-user-images.githubusercontent.com/65781023/290996312-2f15c0ce-53b5-4d2c-bf6d-d2c3b1e26836.JPG?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE3MDI3MzUyNjgsIm5iZiI6MTcwMjczNDk2OCwicGF0aCI6Ii82NTc4MTAyMy8yOTA5OTYzMTItMmYxNWMwY2UtNTNiNS00ZDJjLWJmNmQtZDJjM2IxZTI2ODM2LkpQRz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFJV05KWUFYNENTVkVINTNBJTJGMjAyMzEyMTYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjMxMjE2VDEzNTYwOFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTJiMTE1YzViM2I0YWRiN2RiZDNmZjMzM2FiOTdiYjI2ZmU1ZTgxOWI1ZDdhNDAzMjE5M2NkYjcxNTcyOTdjNjYmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.bTPfqUefY2_F5FbHBGZ0_jK7Z5AJ7NwDmn3wdzT0bC0)

## 사용 방법



## 개발 환경

|구분|verion|
|------|-----|
|Android API|31|
|Android SDK|12(arm 64)|
|Device|Pixel 2 API 28|

사용 방법
1. MySQL을 열고 서버 연결 후 JustDoIt_ScriptFile.sql을 실행한다.
2. JustDoIt/Server/server.js에서 MySQL 개인 password를 입력한다.
3. 터미널에서 JustDoIt/Server 폴더로 이동한 후 npm i를 실행한다.
4. npm start 명령어로 localhost:3123에 연결한다. (에러 발생 시 npm instll mysql 실행해보고 이래도 안되면 말해주셈)
5. 안드로이드 스튜디오를 실행한다.
6. 이후 DB에 insert into로 몇개 추가해서 테스트해본다.
