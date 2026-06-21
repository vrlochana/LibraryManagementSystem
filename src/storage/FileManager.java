package storage;

import model.Book;
import model.Member;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    private static final String BOOKS_FILE = "data/books.txt";
    private static final String MEMBERS_FILE = "data/members.txt";

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                Book book = new Book(parts[0], parts[1], parts[2]);
                book.setAvailable(Boolean.parseBoolean(parts[3]));
                book.setIssuedToMemberId(parts[4].equals("null") ? null : parts[4]);
                book.setIssueDate(parts[5].equals("null") ? null : LocalDate.parse(parts[5]));
                book.setDueDate(parts[6].equals("null") ? null : LocalDate.parse(parts[6]));

                books.add(book);
            }
        } catch (IOException ignored) {
        }

        return books;
    }

    public void saveBooks(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                writer.println(
                        book.getId() + "|" +
                                book.getTitle() + "|" +
                                book.getAuthor() + "|" +
                                book.isAvailable() + "|" +
                                book.getIssuedToMemberId() + "|" +
                                book.getIssueDate() + "|" +
                                book.getDueDate()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving books.");
        }
    }

    public List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(MEMBERS_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                Member member = new Member(parts[0], parts[1], parts[2]);

                if (parts.length > 3 && !parts[3].equals("empty")) {
                    List<String> borrowedBooks = Arrays.asList(parts[3].split(","));
                    for (String bookId : borrowedBooks) {
                        member.borrowBook(bookId);
                    }
                }

                members.add(member);
            }
        } catch (IOException ignored) {
        }

        return members;
    }

    public void saveMembers(List<Member> members) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member member : members) {
                String borrowedBooks = member.getBorrowedBookIds().isEmpty()
                        ? "empty"
                        : String.join(",", member.getBorrowedBookIds());

                writer.println(
                        member.getId() + "|" +
                                member.getName() + "|" +
                                member.getContactInfo() + "|" +
                                borrowedBooks
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving members.");
        }
    }
}