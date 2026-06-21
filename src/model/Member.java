package model;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String id;
    private String name;
    private String contactInfo;
    private List<String> borrowedBookIds;

    public Member() {
        this.borrowedBookIds = new ArrayList<>();
    }

    public Member(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBookIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<String> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void borrowBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return "\nMember ID: " + id +
                "\nName: " + name +
                "\nContact: " + contactInfo +
                "\nBorrowed Books: " + borrowedBookIds;
    }
}