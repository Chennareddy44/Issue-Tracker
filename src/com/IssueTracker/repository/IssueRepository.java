package com.IssueTracker.repository;

import com.IssueTracker.models.Issue;
import com.IssueTracker.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueRepository {

    public Issue findById(int id) {
        String query = "SELECT * FROM issues WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToIssue(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Issue> findAll() {
        List<Issue> issues = new ArrayList<>();
        String query = "SELECT * FROM issues";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                issues.add(mapResultSetToIssue(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issues;
    }

    public void save(Issue issue) {
        String query = "INSERT INTO issues (title, description, status, priority, assignee_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, issue.getTitle());
            stmt.setString(2, issue.getDescription());
            stmt.setString(3, issue.getStatus());
            stmt.setString(4, issue.getPriority());
            stmt.setInt(5, issue.getAssigneeId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    issue.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Issue issue) {
        String query = "UPDATE issues SET title = ?, description = ?, status = ?, priority = ?, assignee_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, issue.getTitle());
            stmt.setString(2, issue.getDescription());
            stmt.setString(3, issue.getStatus());
            stmt.setString(4, issue.getPriority());
            stmt.setInt(5, issue.getAssigneeId());
            stmt.setInt(6, issue.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM issues WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Issue> getIssuesByStatus(String status) {
        List<Issue> issues = new ArrayList<>();
        String query = "SELECT * FROM issues WHERE status = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    issues.add(mapResultSetToIssue(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issues;
    }

    public List<Issue> getIssuesByPriority(String priority) {
        List<Issue> issues = new ArrayList<>();
        String query = "SELECT * FROM issues WHERE priority = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, priority);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    issues.add(mapResultSetToIssue(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issues;
    }

    public List<Issue> getIssuesByUserId(int userId) {
        List<Issue> issues = new ArrayList<>();
        String query = "SELECT * FROM issues WHERE assignee_id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    issues.add(mapResultSetToIssue(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issues;
    }

    private Issue mapResultSetToIssue(ResultSet rs) throws SQLException {
        Issue issue = new Issue();
        issue.setId(rs.getInt("id"));
        issue.setTitle(rs.getString("title"));
        issue.setDescription(rs.getString("description"));
        issue.setStatus(rs.getString("status"));
        issue.setPriority(rs.getString("priority"));
        issue.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        issue.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        issue.setAssigneeId(rs.getInt("assignee_id"));
        return issue;
    }
}