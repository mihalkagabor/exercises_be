CREATE TABLE IF NOT EXISTS  user_data (
                                          user_data_id BIGINT  AUTO_INCREMENT PRIMARY KEY,
                                          user_id BIGINT NOT NULL UNIQUE,
                                          first_name VARCHAR(50) NOT NULL,
                                          last_name VARCHAR(50) NOT NULL,
                                          height INT CHECK (height>140) NOT NULL,
                                          birth_year INT CHECK (birth_year>1900) NOT NULL,
                                          sex VARCHAR(50) NOT NULL,
                                          user_role VARCHAR(50) NOT NULL,

                                          CONSTRAINT fk_user_data_user
                                              FOREIGN KEY (user_id)
                                                  REFERENCES app_user(user_id)
                                                  ON DELETE CASCADE
);
