## Database script:
```sh
CREATE TABLE data (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fuel_price DECIMAL(10, 2) NOT NULL,
    distance DECIMAL(10, 2) NOT NULL,
    consumption DECIMAL(10, 2) NOT NULL,
    language_used VARCHAR(10) NOT NULL
);
```
