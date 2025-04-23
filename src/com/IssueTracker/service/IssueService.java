package com.IssueTracker.service;

import com.IssueTracker.models.Comment;
import com.IssueTracker.models.Issue;
import com.IssueTracker.repository.CommentRepository;
import com.IssueTracker.repository.IssueRepository;

import java.util.List;

public class IssueService {
    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;

    public IssueService(IssueRepository issueRepository, CommentRepository commentRepository) {
        this.issueRepository = issueRepository;
        this.commentRepository = commentRepository;
    }

    public void createIssue(Issue issue) {
        issueRepository.save(issue);
    }

    public List<Issue> viewAllIssues() {
        return issueRepository.findAll();
    }

    public void updateIssue(int issueId, String newTitle, String newDesc, String newStatus, String newPriority,
            Integer newAssigneeId) {
        Issue issue = issueRepository.findById(issueId);
        if (issue == null) {
            System.out.println("Issue not found!");
            return;
        }

        if (newTitle != null && !newTitle.isEmpty())
            issue.setTitle(newTitle);
        if (newDesc != null && !newDesc.isEmpty())
            issue.setDescription(newDesc);
        if (newStatus != null && !newStatus.isEmpty())
            issue.setStatus(newStatus);
        if (newPriority != null && !newPriority.isEmpty())
            issue.setPriority(newPriority);
        if (newAssigneeId != null)
            issue.setAssigneeId(newAssigneeId);

        issueRepository.update(issue);
    }

    public void deleteIssueById(int id) {
        issueRepository.delete(id);
    }

    public List<Issue> getIssuesByStatus(String status) {
        return issueRepository.getIssuesByStatus(status);
    }

    public List<Issue> getIssuesByPriority(String priority) {
        return issueRepository.getIssuesByPriority(priority);
    }

    public List<Issue> getIssuesByUserId(int userId) {
        return issueRepository.getIssuesByUserId(userId);
    }

    public void addCommentToIssue(int issueId, String author, String content) {
        Issue issue = issueRepository.findById(issueId);
        if (issue == null) {
            System.out.println("Issue not found!");
            return;
        }
        Comment comment = new Comment(issueId, author, content);
        commentRepository.save(comment);
        issue.addComment(comment);
    }

    public List<Comment> getCommentsForIssue(int issueId) {
        return commentRepository.findByIssueId(issueId);
    }
}