package com.IssueTracker.runner;

import com.IssueTracker.models.Issue;
import com.IssueTracker.models.User;
import com.IssueTracker.models.Comment;
import com.IssueTracker.service.IssueService;
import com.IssueTracker.service.UserService;
import com.IssueTracker.repository.IssueRepository;
import com.IssueTracker.repository.CommentRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        IssueRepository issueRepo = new IssueRepository();
        CommentRepository commentRepo = new CommentRepository();
        IssueService issueService = new IssueService(issueRepo, commentRepo);

        while (true) {
            System.out.println("\n--- Issue Tracker ---");
            System.out.println("1. Create User");
            System.out.println("2. View All Users");
            System.out.println("3. Create Issue");
            System.out.println("4. View All Issues");
            System.out.println("5. Update Issue");
            System.out.println("6. Delete Issue");
            System.out.println("7. Filter Issues by Status");
            System.out.println("8. Filter Issues by Priority");
            System.out.println("9. Filter Issues by Assigned User");
            System.out.println("10. Add Comment to Issue");
            System.out.println("11. View Comments of an Issue");
            System.out.println("12. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    userService.createUser(name, email);
                    System.out.println("User created!");
                    break;

                case 2:
                    for (User user : userService.getAllUsers()) {
                        System.out.println(user);
                    }
                    break;

                case 3:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter status (e.g., OPEN, IN_PROGRESS, RESOLVED, CLOSED): ");
                    String status = scanner.nextLine();
                    System.out.print("Enter priority (e.g., High, Medium, Low): ");
                    String priority = scanner.nextLine();
                    System.out.print("Enter assigned user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    Issue issue = new Issue(title, desc, status, priority, userId);
                    issueService.createIssue(issue);
                    System.out.println("Issue created!");
                    break;

                case 4:
                    for (Issue i : issueService.viewAllIssues()) {
                        System.out.println(i);
                    }
                    break;

                case 5:
                    System.out.print("Enter issue ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new title (leave blank to keep same): ");
                    String titleUpdate = scanner.nextLine();

                    System.out.print("Enter new description (leave blank to keep same): ");
                    String descUpdate = scanner.nextLine();

                    System.out.print(
                            "Enter new status (e.g., OPEN, IN_PROGRESS, RESOLVED, CLOSED, leave blank to keep same): ");
                    String statusUpdate = scanner.nextLine();

                    System.out.print("Enter new priority (e.g., High, Medium, Low, leave blank to keep same): ");
                    String priorityUpdate = scanner.nextLine();

                    System.out.print("Enter new assignee ID (leave blank to keep same): ");
                    String assigneeInput = scanner.nextLine();
                    Integer newAssigneeId = assigneeInput.isEmpty() ? null : Integer.parseInt(assigneeInput);

                    issueService.updateIssue(id, titleUpdate, descUpdate, statusUpdate, priorityUpdate, newAssigneeId);
                    System.out.println("Issue updated!");
                    break;

                case 6:
                    System.out.print("Enter issue ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    issueService.deleteIssueById(deleteId);
                    System.out.println("Issue deleted!");
                    break;

                case 7:
                    System.out.print("Enter status to filter: ");
                    String fStatus = scanner.nextLine();
                    for (Issue i : issueService.getIssuesByStatus(fStatus)) {
                        System.out.println(i);
                    }
                    break;

                case 8:
                    System.out.print("Enter priority to filter: ");
                    String fPriority = scanner.nextLine();
                    for (Issue i : issueService.getIssuesByPriority(fPriority)) {
                        System.out.println(i);
                    }
                    break;

                case 9:
                    System.out.print("Enter user ID to filter: ");
                    int fUserId = scanner.nextInt();
                    scanner.nextLine();
                    for (Issue i : issueService.getIssuesByUserId(fUserId)) {
                        System.out.println(i);
                    }
                    break;

                case 10:
                    System.out.print("Enter issue ID: ");
                    int issueId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter your name: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter comment: ");
                    String content = scanner.nextLine();

                    issueService.addCommentToIssue(issueId, author, content);
                    System.out.println("Comment added!");
                    break;

                case 11:
                    System.out.print("Enter issue ID to view comments: ");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    List<Comment> comments = issueService.getCommentsForIssue(id);
                    if (comments.isEmpty()) {
                        System.out.println("No comments.");
                    } else {
                        for (Comment c : comments) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 12:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}