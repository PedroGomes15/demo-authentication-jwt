CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users
(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role smallint NOT NULL
)