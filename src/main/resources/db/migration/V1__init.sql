-- Users table with role column
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(50) NOT NULL DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS tasks (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  title varchar(255) NOT NULL,
  description text,
  completed boolean NOT NULL DEFAULT false,
  priority_type varchar(10) NOT NULL,
  created_at timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamptz DEFAULT CURRENT_TIMESTAMP
);

-- Index for faster lookups by email
CREATE INDEX idx_users_email ON users(email);

-- Indexes
CREATE INDEX IF NOT EXISTS idx_tasks_user_id ON tasks(user_id);
CREATE INDEX IF NOT EXISTS idx_tasks_created_at ON tasks(created_at);
CREATE INDEX IF NOT EXISTS idx_tasks_title_lower ON tasks(lower(title));
