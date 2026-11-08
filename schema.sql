CREATE DATABASE IF NOT EXISTS job_tracker;
USE job_tracker;

CREATE TABLE IF NOT EXISTS applications (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            company VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    applied_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Applied',
    follow_up_date DATE NOT NULL,
    job_link VARCHAR(500) NULL,
    location VARCHAR(100) NULL,
    notes TEXT NULL
    );

CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
    );