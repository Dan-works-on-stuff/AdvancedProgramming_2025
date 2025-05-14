package org.example;

import java.sql.*;

public class ArtistDAO {
    private final Connection connection;

    public ArtistDAO(Connection connection) {
        this.connection = connection;
    }

    public int addArtist(String name) throws SQLException {
        String sql = "INSERT INTO artists (name) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Failed to get generated artist ID.");
            }
        }
    }

    public Artist getArtistById(int id) throws SQLException {
        String sql = "SELECT * FROM artists WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Artist(rs.getInt("id"), rs.getString("name"));
            }
            return null;
        }
    }
}
