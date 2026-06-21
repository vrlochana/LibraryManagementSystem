import exception.BookNotAvailableException;
import exception.BookNotFoundException;
import exception.MemberNotFoundException;

import model.Book;
import model.Member;

import service.LibraryService;

import storage.FileManager;

import util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LibraryService library = new LibraryService();
        FileManager fileManager = new FileManager();

        library.setBooks(fileManager.loadBooks());
        library.setMembers(fileManager.loadMembers());

        boolean running = true;

        while (running) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Register Member");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. List Books");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (!InputValidator.isValidMenuChoice(choice)) {
                    System.out.println("Invalid option.");
                    continue;
                }

            } catch (Exception e) {
                System.out.println("Enter numeric value.");
                continue;
            }

            try {

                switch (choice) {

                    case 1:

                        System.out.print("Book ID: ");
                        String bookId = scanner.nextLine();

                        System.out.print("Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Author: ");
                        String author = scanner.nextLine();

                        if (!InputValidator.isValidId(bookId)
                                || !InputValidator.isValidText(title)
                                || !InputValidator.isValidText(author)) {

                            System.out.println("Invalid input.");
                            break;
                        }

                        library.addBook(
                                new Book(bookId, title, author)
                        );

                        System.out.println("Book added.");
                        break;

                    case 2:

                        System.out.print("Search title: ");

                        List<Book> books =
                                library.searchBookByTitle(
                                        scanner.nextLine()
                                );

                        if (books.isEmpty()) {
                            System.out.println("No books found.");
                        } else {
                            books.forEach(System.out::println);
                        }

                        break;

                    case 3:

                        System.out.print("Member ID: ");
                        String memberId = scanner.nextLine();

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Contact: ");
                        String contact = scanner.nextLine();

                        library.registerMember(
                                new Member(
                                        memberId,
                                        name,
                                        contact
                                )
                        );

                        System.out.println("Member registered.");

                        break;

                    case 4:

                        System.out.print("Member ID: ");
                        String issueMember =
                                scanner.nextLine();

                        System.out.print("Book ID: ");
                        String issueBook =
                                scanner.nextLine();

                        library.issueBook(
                                issueMember,
                                issueBook
                        );

                        System.out.println("Book issued.");

                        break;

                    case 5:

                        System.out.print("Book ID: ");

                        double fine =
                                library.returnBook(
                                        scanner.nextLine()
                                );

                        System.out.println(
                                "Book returned."
                        );

                        System.out.println(
                                "Fine: Rs" + fine
                        );

                        break;

                    case 6:

                        List<Book> allBooks =
                                library.getAllBooks();

                        if (allBooks.isEmpty()) {
                            System.out.println("No books.");
                        } else {
                            allBooks.forEach(System.out::println);
                        }

                        break;

                    case 7:

                        fileManager.saveBooks(
                                library.getAllBooks()
                        );

                        fileManager.saveMembers(
                                library.getAllMembers()
                        );

                        running = false;

                        System.out.println(
                                "Data saved. Goodbye."
                        );

                        break;
                }

            } catch (
                    BookNotFoundException |
                    MemberNotFoundException |
                    BookNotAvailableException e
            ) {

                System.out.println(
                        e.getMessage()
                );

            } catch (Exception e) {

                System.out.println(
                        "Operation failed."
                );
            }
        }

        scanner.close();
    }
}