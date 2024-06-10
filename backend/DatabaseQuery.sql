CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL, 
    password VARCHAR(50) NOT NULL
);

CREATE TABLE downloads(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    links VARCHAR(100) NOT NULL
);

CREATE TABLE solutions(
    id SERIAL PRIMARY KEY,
    authorid VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
);