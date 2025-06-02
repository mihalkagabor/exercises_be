CREATE TABLE IF NOT EXISTS accessory(
                                        accessory_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        accessory_name VARCHAR(255) NOT NULL UNIQUE
);