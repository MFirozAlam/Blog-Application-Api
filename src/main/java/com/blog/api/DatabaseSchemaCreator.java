package com.blog.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSchemaCreator {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/blog_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Firoz@123";

    public static void main(String[] args) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create database schema
            createSchema(conn);

            // Close database connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error creating database schema: " + e.getMessage());
        }
    }

    private static void createSchema(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        // Create users table
        stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "username VARCHAR(50) NOT NULL, " +
                "password VARCHAR(255) NOT NULL, " +
                "email VARCHAR(100) NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                ")");

        // Create posts table
        stmt.execute("CREATE TABLE IF NOT EXISTS posts (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "title VARCHAR(255) NOT NULL, " +
                "content TEXT NOT NULL, " +
                "author_id INT NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "published_at TIMESTAMP, " +
                "FOREIGN KEY (author_id) REFERENCES users(id)" +
                ")");

        // Create comments table
        stmt.execute("CREATE TABLE IF NOT EXISTS comments (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "post_id INT NOT NULL, " +
                "author_id INT NOT NULL, " +
                "content TEXT NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "parent_comment_id INT, " +
                "FOREIGN KEY (post_id) REFERENCES posts(id), " +
                "FOREIGN KEY (author_id) REFERENCES users(id), " +
                "FOREIGN KEY (parent_comment_id) REFERENCES comments(id)" +
                ")");

        // Create categories table
        stmt.execute("CREATE TABLE IF NOT EXISTS categories (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "description TEXT" +
                ")");

        // Create post_categories table
        stmt.execute("CREATE TABLE IF NOT EXISTS post_categories (" +
                "post_id INT NOT NULL, " +
                "category_id INT NOT NULL, " +
                "PRIMARY KEY (post_id, category_id), " +
                "FOREIGN KEY (post_id) REFERENCES posts(id), " +
                "FOREIGN KEY (category_id) REFERENCES categories(id)" +
                ")");

        // Create tags table
        stmt.execute("CREATE TABLE IF NOT EXISTS tags (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL" +
                ")");

        // Create post_tags table
        stmt.execute("CREATE TABLE IF NOT EXISTS post_tags (" +
                "post_id INT NOT NULL, " +
                "tag_id INT NOT NULL, " +
                "PRIMARY KEY (post_id, tag_id), " +
                "FOREIGN KEY (post_id) REFERENCES posts(id), " +
                "FOREIGN KEY (tag_id) REFERENCES tags(id)" +
                ")");

        stmt.close();
    }
}

