package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Get database connection
            DatabaseConnection db = DatabaseConnection.getInstance();
            Connection conn = db.getConnection();

            // Test ArtistDAO
            ArtistDAO artistDao = new ArtistDAO(conn);
            int artistId = artistDao.addArtist("The Beatles");
            System.out.println("Inserted Artist ID: " + artistId);

            Artist artist = artistDao.getArtistById(artistId);
            System.out.println("Retrieved Artist: " + artist.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}