CREATE TABLE IF NOT EXISTS workout(
                                      workout_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      exercises_id BIGINT NOT NULL,
                                      user_data_id BIGINT NOT NULL,
                                      start_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                      end_date DATETIME NULL,
                                      workout_duration INT  AS (
                                          CASE
                                              WHEN end_date IS NOT NULL THEN  TIMESTAMPDIFF(SECOND,start_date,end_date)
                                              ELSE NULL
                                              END
                                          ) STORED,
                                      exercise_amount INT  CHECK (exercise_amount>=0) NOT NULL,
                                      exercise_weight DECIMAL(5,1) DEFAULT 0.0,

                                      CONSTRAINT fk_workout_exercises
                                          FOREIGN KEY (exercises_id)
                                              REFERENCES exercises(exercises_id)
                                              ON DELETE RESTRICT,

                                      CONSTRAINT fk_workout_user_data
                                          FOREIGN KEY(user_data_id)
                                              REFERENCES user_data(user_data_id)
                                              ON DELETE RESTRICT
);