-- create_table.sql

-- 데이터베이스 선택 
CREATE SCHEMA IF NOT EXISTS `JustDoIt` DEFAULT CHARACTER SET utf8mb3 ;
USE `JustDoIt` ;

-- 테이블 생성
CREATE TABLE IF NOT EXISTS todo (
    user_id INT NOT NULL,
    todo_name VARCHAR(50) NOT NULL,
    present_progress INT DEFAULT 0,
    total_progress INT NOT NULL,
    deadline DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS daily_todo (
    user_id INT NOT NULL,
    todo_name VARCHAR(50) NOT NULL,
    present_progress INT DEFAULT 0,
    total_progress INT NOT NULL,
    create_date DATE DEFAULT (CURRENT_DATE)
);
