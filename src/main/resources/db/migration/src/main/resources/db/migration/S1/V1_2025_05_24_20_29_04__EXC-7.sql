CREATE TABLE IF NOT EXISTS exercises (
                                         exercises_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         difficulty_level_id BIGINT NOT NULL,
                                         accessory_id BIGINT NOT NULL,
                                         exercise_name VARCHAR(255) NOT NULL,
                                        is_free_weight BOOLEAN NOT NULL,

                                         CONSTRAINT fk_exercises_difficulty_level
                                             FOREIGN KEY (difficulty_level_id)
                                                 REFERENCES difficulty_level (difficulty_level_id)
                                                 ON DELETE RESTRICT,

                                         CONSTRAINT fk_exercises_accessory
                                            FOREIGN KEY (accessory_id)
                                            REFERENCES accessory(accessory_id)
                                            ON DELETE RESTRICT
);