-- Users table with role column
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(50) NOT NULL DEFAULT 'USER'
);

-- Index for faster lookups by email
CREATE INDEX idx_users_email ON users(email);