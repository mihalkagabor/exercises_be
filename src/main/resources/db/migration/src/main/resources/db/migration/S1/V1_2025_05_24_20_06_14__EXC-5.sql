CREATE TABLE IF NOT EXISTS difficulty_level (
                                                difficulty_level_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                difficulty_level_level INT CHECK (difficulty_level_level BETWEEN 1 AND 10 ) NOT NULL UNIQUE,
                                                difficulty_level_description VARCHAR(255) NOT NULL UNIQUE

) ;