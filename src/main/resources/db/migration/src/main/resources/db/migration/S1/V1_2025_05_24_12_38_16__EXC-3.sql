CREATE TABLE IF NOT EXISTS  body_weight(
                                           body_weight_id BIGINT  AUTO_INCREMENT PRIMARY KEY,
                                           user_data_id BIGINT NOT NULL ,
                                           weight DECIMAL(4,1) NOT NULL,
                                           measure_date DATETIME DEFAULT CURRENT_TIMESTAMP,

                                           CONSTRAINT fk_body_weight_user_data
                                               FOREIGN KEY(user_data_id)
                                                   REFERENCES user_data  (user_data_id)
                                                   ON DELETE CASCADE

);