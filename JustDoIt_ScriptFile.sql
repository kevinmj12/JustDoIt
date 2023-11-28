-- create_table.sql

-- 데이터베이스 선택 
CREATE SCHEMA IF NOT EXISTS `JustDoIt` DEFAULT CHARACTER SET utf8mb3 ;
USE `JustDoIt` ;

-- 테이블 생성
CREATE TABLE IF NOT EXISTS user_info (
	id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(20) NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    user_pw VARCHAR(20) NOT NULL,
    user_birthday DATETIME NOT NULL,
	user_email VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS todo (
    user_id INT NOT NULL,
    todo_name VARCHAR(50) NOT NULL,
    present_progress INT DEFAULT 0,
    deadline DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_info(id)
);

CREATE TABLE IF NOT EXISTS daily_todo (
    user_id INT NOT NULL,
    todo_name VARCHAR(50) NOT NULL,
    present_progress INT DEFAULT 0,
    create_date DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (user_id) REFERENCES user_info(id)
);

