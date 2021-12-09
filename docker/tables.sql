CREATE TABLE messages
(
    id TEXT PRIMARY KEY,
    timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    channel TEXT NOT NULL
);
CREATE TABLE predefined_answer_category
(
    id TEXT PRIMARY KEY,
    version smallint not null default 1
);
CREATE TABLE predefined_answer
(
    id TEXT primary key,
    text TEXT NOT NULL,
    category TEXT NOT NULL REFERENCES predefined_answer_category (id),
    version smallint not null default 1
);
