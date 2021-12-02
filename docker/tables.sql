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
    answerId TEXT NOT NULL,
    categoryId TEXT NOT NULL,
    CONSTRAINT FK_AnswerCategory_Answer FOREIGN KEY (answerId) REFERENCES predefined_answer(id)
);