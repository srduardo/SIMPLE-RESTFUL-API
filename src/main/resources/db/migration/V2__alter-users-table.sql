ALTER TABLE users RENAME COLUMN phone TO password;
ALTER TABLE users ALTER COLUMN password TYPE VARCHAR(50);
ALTER TABLE users ALTER COLUMN password SET NOT NULL;