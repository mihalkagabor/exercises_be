CREATE TABLE refresh_tokens (
                                refresh_token_id SERIAL PRIMARY KEY,
                                token VARCHAR(255) NOT NULL UNIQUE,
                                expiry_date TIMESTAMP NOT NULL,
                                user_id BIGINT NOT NULL,
                                CONSTRAINT fk_refresh_tokens_user
                                    FOREIGN KEY (user_id) REFERENCES app_user(user_id)
                                        ON DELETE CASCADE
);