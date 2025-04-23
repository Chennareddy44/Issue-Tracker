package com.IssueTracker.repository;

import com.IssueTracker.models.Comment;
import com.IssueTracker.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository {

    public void save(Comment comment) {
        String query = "INSERT INTO comments (issue_id, author, content) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, comment.getIssueId());
            stmt.setString(2, comment.getAuthor());
            stmt.setString(3, comment.getContent());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    comment.setCommentId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> findByIssueId(int issueId) {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT * FROM comments WHERE issue_id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, issueId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String author = rs.getString("author");
                    String content = rs.getString("content");
                    comments.add(new Comment(issueId, id, author, content));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
}