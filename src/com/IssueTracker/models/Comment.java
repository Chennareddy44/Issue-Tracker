package com.IssueTracker.models;

public class Comment {
    private int commentId;
    private int issueId;
    private String author;
    private String content;

    public Comment(int issueId, int commentId, String author, String content) {
        this.commentId = commentId;
        this.issueId = issueId;
        this.author = author;
        this.content = content;
    }

    public Comment(int issueId, String author, String content) {
        this.issueId = issueId;
        this.author = author;
        this.content = content;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getIssueId() {
        return issueId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", issueId=" + issueId +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}