CREATE TABLE authors (
    author_id SERIAL PRIMARY KEY,     -- Уникальный ID автора
    first_name VARCHAR(100) NOT NULL, -- Имя автора
    last_name VARCHAR(100) NOT NULL   -- Фамилия автора
);

CREATE TABLE book_tracking (
    tracking_id SERIAL PRIMARY KEY,      -- Уникальный ID записи
    book_id INT NOT NULL,                -- ID книги (из books_db)
    borrowed_at TIMESTAMP DEFAULT NOW(), -- Время взятия книги
    return_by TIMESTAMP                  -- Время, когда книгу нужно вернуть
);

CREATE TABLE books (
    book_id SERIAL PRIMARY KEY,      -- Уникальный ID книги
    isbn VARCHAR(17) NOT NULL UNIQUE, -- ISBN книги
    title VARCHAR(255) NOT NULL,      -- Название книги
    genre VARCHAR(100),               -- Жанр книги
    description TEXT,                 -- Описание книги
    author_id INT NOT NULL            -- ID автора
);

INSERT INTO authors (first_name, last_name) VALUES
('George', 'Orwell'),
('J.K.', 'Rowling'),
('Mark', 'Twain'),
('Jane', 'Austen'),
('Charles', 'Dickens'),
('Leo', 'Tolstoy'),
('F. Scott', 'Fitzgerald'),
('Ernest', 'Hemingway'),
('Herman', 'Melville'),
('William', 'Shakespeare');

INSERT INTO books (isbn, title, genre, description, author_id) VALUES
('978-0-452-28423-4', '1984', 'Dystopian', 'A novel about a totalitarian regime', 1),
('978-0-7475-3269-9', 'Harry Potter and the Sorcerer''s Stone', 'Fantasy', 'A young wizard begins his journey', 2),
('978-0-452-27241-5', 'The Adventures of Tom Sawyer', 'Adventure', 'A classic adventure novel', 3),
('978-1-59308-201-7', 'Pride and Prejudice', 'Romance', 'A story about love and society', 4),
('978-0-14-143960-0', 'Great Expectations', 'Drama', 'A novel of personal development', 5),
('978-0-14-044924-2', 'War and Peace', 'Historical', 'A historical novel set in the Napoleonic era', 6),
('978-0-7432-7356-5', 'The Great Gatsby', 'Tragedy', 'A critique of the American Dream', 7),
('978-0-684-80146-0', 'The Old Man and the Sea', 'Literature', 'An epic struggle between man and nature', 8),
('978-0-553-21311-7', 'Moby-Dick', 'Adventure', 'A man''s obsession with a great white whale', 9),
('978-0-19-283926-8', 'Hamlet', 'Tragedy', 'A Shakespearean tragedy about revenge', 10),

('978-0-452-28423-5', 'Animal Farm', 'Political satire', 'A story about rebellion and power', 1),
('978-0-7475-3270-5', 'Harry Potter and the Chamber of Secrets', 'Fantasy', 'A young wizard faces new challenges', 2),
('978-0-452-27241-6', 'The Adventures of Huckleberry Finn', 'Adventure', 'A journey down the Mississippi River', 3),
('978-1-59308-202-4', 'Emma', 'Romance', 'A novel about a young woman''s matchmaking efforts', 4),
('978-0-14-143956-3', 'David Copperfield', 'Drama', 'A novel about growing up in the Victorian era', 5),
('978-0-14-044930-3', 'Anna Karenina', 'Tragedy', 'A complex story about love and betrayal', 6),
('978-0-7432-7357-2', 'Tender is the Night', 'Tragedy', 'A novel about the lives of expatriates', 7),
('978-0-684-80147-7', 'For Whom the Bell Tolls', 'War', 'A novel set during the Spanish Civil War', 8),
('978-0-553-21312-4', 'Billy Budd', 'Adventure', 'A story about innocence and evil at sea', 9),
('978-0-19-283927-5', 'Macbeth', 'Tragedy', 'A tale of ambition and fate', 10),

('978-0-452-28423-6', 'Coming Up for Air', 'Dystopian', 'A man reflects on the changing world', 1),
('978-0-7475-3271-2', 'Harry Potter and the Prisoner of Azkaban', 'Fantasy', 'A dark figure from the past returns', 2),
('978-0-452-27241-7', 'Life on the Mississippi', 'Adventure', 'Mark Twain''s memoir of his time as a steamboat pilot', 3),
('978-1-59308-203-1', 'Sense and Sensibility', 'Romance', 'A novel about two sisters and their contrasting approaches to life', 4),
('978-0-14-143952-5', 'Bleak House', 'Drama', 'A complex novel about the legal system and society', 5),
('978-0-14-044926-6', 'Resurrection', 'Philosophical', 'A novel about moral awakening', 6),
('978-0-7432-7358-9', 'This Side of Paradise', 'Tragedy', 'A young man navigates love and ambition', 7),
('978-0-684-80148-4', 'A Farewell to Arms', 'War', 'A love story set during World War I', 8),
('978-0-553-21313-1', 'White-Jacket', 'Adventure', 'A semi-autobiographical novel about life aboard a warship', 9),
('978-0-19-283928-2', 'Othello', 'Tragedy', 'A story of jealousy and betrayal', 10);
select * from authors