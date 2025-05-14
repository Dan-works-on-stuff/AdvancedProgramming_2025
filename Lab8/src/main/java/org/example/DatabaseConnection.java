package org.example;

import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private final String USER = "sa";
    private final String PASSWORD = "";

    private DatabaseConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            createTables();
        } catch (SQLException e) {
            System.err.println("Error creating connection: " + e.getMessage());
            throw e;
        }
    }

    private void createTables() {
        try (Statement stmt = connection.createStatement()) {
            // Execute the schema.sql content
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS artists (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS genres (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS albums (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "release_year INT, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "artist_id INT, " +
                    "FOREIGN KEY (artist_id) REFERENCES artists(id))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS album_genres (" +
                    "album_id INT, " +
                    "genre_id INT, " +
                    "PRIMARY KEY (album_id, genre_id), " +
                    "FOREIGN KEY (album_id) REFERENCES albums(id), " +
                    "FOREIGN KEY (genre_id) REFERENCES genres(id))");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}