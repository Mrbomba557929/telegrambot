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
    word VARCHAR(45) PRIMARY KEY
);

CREATE TABLE translations
(
    translation VARCHAR(50) PRIMARY KEY
);

CREATE TABLE words_translations
(
    word VARCHAR(45),
    translation VARCHAR(50),
    user_id INTEGER,
    PRIMARY KEY (word, translation, user_id)
);

CREATE TABLE modules_words
(
    module_id INTEGER,
    word VARCHAR(45),
    PRIMARY KEY (module_id, word)
);

ALTER TABLE modules
    ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE words_translations
    ADD FOREIGN KEY (word) REFERENCES words (word);

ALTER TABLE words_translations
    ADD FOREIGN KEY (translation) REFERENCES translations (translation);

ALTER TABLE words_translations
    ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE modules_words
    ADD FOREIGN KEY (module_id) REFERENCES modules (id);

ALTER TABLE modules_words
    ADD FOREIGN KEY (word) REFERENCES words (word);