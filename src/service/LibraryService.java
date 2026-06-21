package service;

import exception.BookNotAvailableException;
import exception.BookNotFoundException;
import exception.MemberNotFoundException;
import model.Book;
import model.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryService {

    private List<Book> books;
    private HashMap<String, Member> members;
    private FineCalculator fineCalculator;

    public LibraryService() {
        books = new ArrayList<>();
        members = new HashMap<>();
        fineCalculator = new FineCalculator();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.put(member.getId(), member);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    public Book searchBookById(String bookId) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }
        throw new BookNotFoundException("Book not found with ID: " + bookId);
    }

    public List<Book> searchBookByTitle(String title) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    public Member searchMemberById(String memberId) throws MemberNotFoundException {
        Member member = members.get(memberId);

        if (member == null) {
            throw new MemberNotFoundException("Member not found with ID: " + memberId);
        }

        return member;
    }

    public void issueBook(String memberId, String bookId)
            throws BookNotFoundException, MemberNotFoundException, BookNotAvailableException {

        Book book = searchBookById(bookId);
        Member member = searchMemberById(memberId);

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is already issued.");
        }

        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(14);

        book.setAvailable(false);
        book.setIssuedToMemberId(memberId);
        book.setIssueDate(issueDate);
        book.setDueDate(dueDate);

        member.borrowBook(bookId);
    }

    public double returnBook(String bookId) throws BookNotFoundException {
        Book book = searchBookById(bookId);

        if (book.isAvailable()) {
            throw new IllegalStateException("This book is not currently issued.");
        }

        double fine = fineCalculator.calculateFine(book.getDueDate(), LocalDate.now());

        String memberId = book.getIssuedToMemberId();
        if (memberId != null && members.containsKey(memberId)) {
            members.get(memberId).returnBook(bookId);
        }

        book.setAvailable(true);
        book.setIssuedToMemberId(null);
        book.setIssueDate(null);
        book.setDueDate(null);

        return fine;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setMembers(List<Member> memberList) {
        members.clear();

        for (Member member : memberList) {
            members.put(member.getId(), member);
        }
    }
}