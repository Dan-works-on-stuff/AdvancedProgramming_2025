CREATE TABLE IF NOT EXISTS artists (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS genres (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS albums (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      release_year INT,
                                      title VARCHAR(255) NOT NULL,
    artist_id INT,
    FOREIGN KEY (artist_id) REFERENCES artists(id)
    );

CREATE TABLE IF NOT EXISTS album_genres (
                                            album_id INT,
                                            genre_id INT,
                                            PRIMARY KEY (album_id, genre_id),
    FOREIGN KEY (album_id) REFERENCES albums(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
    );