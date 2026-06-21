package model;

import java.time.LocalDate;

public class Book {

    private String id;
    private String title;
    private String author;

    private boolean available;

    private String issuedToMemberId;

    private LocalDate issueDate;
    private LocalDate dueDate;

    public Book() {
        this.available = true;
    }

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getIssuedToMemberId() {
        return issuedToMemberId;
    }

    public void setIssuedToMemberId(String issuedToMemberId) {
        this.issuedToMemberId = issuedToMemberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "\nBook ID: " + id +
                "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nAvailable: " + available;
    }
}