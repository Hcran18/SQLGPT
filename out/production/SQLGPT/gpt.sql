CREATE TABLE Library (
    library_id INT PRIMARY KEY,
    location VARCHAR(50) NOT NULL
);

CREATE TABLE Book (
    book_id INT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL
);

CREATE TABLE LibraryBook (
    library_id INT,
    book_id INT,
    copies_available INT,
    PRIMARY KEY (library_id, book_id),
    FOREIGN KEY (library_id) REFERENCES Library(library_id),
    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

CREATE TABLE People (
    person_id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE CheckedOutBooks (
    person_id INT,
    book_id INT,
    checkout_date DATE,
    return_date DATE,
    PRIMARY KEY (person_id, book_id),
    FOREIGN KEY (person_id) REFERENCES People(person_id),
    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);