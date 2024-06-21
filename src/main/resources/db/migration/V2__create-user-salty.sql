CREATE TABLE users_salty
(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    salty character varying(255) NOT NULL
)