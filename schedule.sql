CREATE SCHEMA schedulejpa;

USE schedule;

CREATE TABLE user
(
    ID          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '유저 식별자',
    EMAIL       VARCHAR(255) NOT NULL COMMENT '이메일',
    USER_NAME   VARCHAR(255) NOT NULL COMMENT '유저이름',
    CREATE_DATE DATETIME(6)  NOT NULL COMMENT '가입일',
    UPDATE_DATE DATETIME(6)  NOT NULL COMMENT '수정일'
);

CREATE TABLE schedule
(
    ID          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '일정 식별자',
    TITLE       VARCHAR(255) NOT NULL COMMENT '일정 제목',
    CONTENTS    LONGTEXT     NOT NULL COMMENT '일정 내용',
    USER_ID     BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES user (ID),
    CREATE_DATE DATETIME(6)  NOT NULL COMMENT '작성일',
    UPDATE_DATE DATETIME(6)  NOT NULL COMMENT '수정일'
);

CREATE TABLE comment
(
    ID          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '댓글 식별자',
    CONTENTS    varchar(255) NOT NULL COMMENT '댓글 내용',
    USER_ID     BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES user (ID),
    SCHEDULE_ID BIGINT,
    FOREIGN KEY (SCHEDULE_ID) REFERENCES schedule (ID),
    CREATE_DATE DATETIME(6)  NOT NULL COMMENT '작성일',
    UPDATE_DATE DATETIME(6)  NOT NULL COMMENT '수정일'
);

