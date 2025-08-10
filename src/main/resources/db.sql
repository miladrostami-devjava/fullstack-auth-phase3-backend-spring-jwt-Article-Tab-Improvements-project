
CREATE DATABASE jwt_auth_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE jwt_auth_db;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER'
);


ALTER TABLE user ADD COLUMN profile_photo VARCHAR(255);
ALTER TABLE user ADD COLUMN email VARCHAR(255);

UPDATE user
SET email = 'farhad@gmail.com',
    profile_photo = 'default.jpg'
WHERE username = 'farhad';


ALTER TABLE user MODIFY COLUMN email VARCHAR(255) NOT NULL;

drop table user;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    profile_photo VARCHAR(255),
    role VARCHAR(255)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;





-- phase-3 : added articles management

CREATE TABLE articles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


select * from  articles;
select * from user where username = 'milad';

select * from user 
