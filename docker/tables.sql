CREATE TABLE messages
(
    id TEXT PRIMARY KEY,
    timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    channel TEXT NOT NULL
);

CREATE TABLE predefined_answer
(
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    text TEXT NOT NULL
);
CREATE TABLE predefined_answer_category
(
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL
);
CREATE TABLE answer_category
(
    answer_id TEXT NOT NULL REFERENCES predefined_answer (id),
    category_id TEXT NOT NULL REFERENCES predefined_answer_category (id)
);