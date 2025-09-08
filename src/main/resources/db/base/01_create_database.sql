CREATE DATABASE IF NOT EXISTS `sistema_chaves`
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci
    DEFAULT ENCRYPTION='N';

USE `sistema_chaves`;

CREATE USER IF NOT EXISTS 'sgc_app_dev'@'localhost' IDENTIFIED BY 'Daniel123';

GRANT ALL PRIVILEGES ON sgc_db.* TO 'sgc_app_dev'@'localhost';
FLUSH PRIVILEGES;