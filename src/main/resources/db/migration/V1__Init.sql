CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    username VARCHAR(100),
    is_bot BOOLEAN,
    language_code VARCHAR(5)
);

CREATE TABLE modules
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    created_at DATE,
    user_id INTEGER
);

CREATE TABLE words
(
    id SERIAL PRIMARY KEY,
    word VARCHAR(45),
    module_id INTEGER
);

CREATE TABLE translations
(
    id SERIAL PRIMARY KEY,
    translation VARCHAR(50) UNIQUE
);

CREATE TABLE words_translations
(
    word_id INTEGER,
    translation_id INTEGER,
    PRIMARY KEY (word_id, translation_id)
);

ALTER TABLE modules
    ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE words_translations
    ADD FOREIGN KEY (word_id) REFERENCES words (id);

ALTER TABLE words_translations
    ADD FOREIGN KEY (translation_id) REFERENCES translations (id);

ALTER TABLE words
    ADD FOREIGN KEY (module_id) REFERENCES modules (id);