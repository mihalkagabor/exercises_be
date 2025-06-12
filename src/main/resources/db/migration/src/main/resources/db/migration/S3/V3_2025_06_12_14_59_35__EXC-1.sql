ALTER TABLE exercises
    ADD COLUMN body_part_id BIGINT;

ALTER TABLE exercises
    ADD CONSTRAINT fk_exercises_body_part
        FOREIGN KEY (body_part_id)
            REFERENCES body_part (body_part_id)
            ON DELETE SET NULL;