DROP TABLE IF EXISTS customer cascade;

CREATE TABLE customer (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  phone VARCHAR(50) NOT NULL,
  UNIQUE KEY customer_phone (phone),
  UNIQUE KEY customer_email (email)
);

DROP TABLE IF EXISTS address cascade;

CREATE TABLE address(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  country VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  details VARCHAR(150) NOT NULL,
  username VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES customer(username)
);

DROP TABLE IF EXISTS book cascade;

CREATE TABLE book(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  author VARCHAR(150) NOT NULL,
  price DECIMAL NOT NULL,
  stock INT NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS orders cascade;

CREATE TABLE orders(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  order_date DATE NOT NULL,
  total_price DECIMAL NOT NULL,
  total_book_count INT NOT NULL DEFAULT 0,
  FOREIGN KEY (username) REFERENCES customer(username)
);

DROP TABLE IF EXISTS order_content;

CREATE TABLE order_content(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT NOT NULL,
  book_id BIGINT NOT NULL,
  count INT NOT NULL,
  total_price DECIMAL NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (book_id) REFERENCES book(id)
);