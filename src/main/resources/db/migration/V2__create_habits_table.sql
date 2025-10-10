CREATE TABLE habits (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    quote TEXT,
    start_date DATE NOT NULL,
    time_section VARCHAR(50) NOT NULL,
    is_completed BOOLEAN NOT NULL DEFAULT FALSE,
    goal_days INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_habit_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE INDEX idx_habits_user_id ON habits(user_id);