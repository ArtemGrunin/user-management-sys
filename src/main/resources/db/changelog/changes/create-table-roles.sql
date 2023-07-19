CREATE TABLE IF NOT EXISTS public.roles
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

-- Rollback script:
-- DROP TABLE public.roles;