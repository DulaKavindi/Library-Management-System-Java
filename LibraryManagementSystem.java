import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true; // Default to available
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println();
    }
}

// Library class
class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    // Add a book to the library, with a flag to control message display
    public void addBook(Book book, boolean isInteractive) {
        books.add(book);
        if (isInteractive) {
            System.out.println("Book added successfully.");
        }
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("Library Books:");
        for (Book book : books) {
            book.displayInfo();
        }
    }

    public void borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("You have borrowed the book: " + isbn);
                } else {
                    System.out.println("The book is already borrowed.");
                }
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("You have returned the book: " + isbn);
                } else {
                    System.out.println("The book was not borrowed.");
                }
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }
}

// Main class
public class LibraryManagementSystem {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Add sample books without printing messages
        library.addBook(new Book("Madol Doova", "Martin Wickramasinghe", "978"), false);
        library.addBook(new Book("Yuganthaya", "Martin Wickramasinghe", "955"), false);
        library.addBook(new Book("Golu Hadawatha", "Karunasena Jayalath", "210"), false);
        library.addBook(new Book("The Road from Elephant Pass", "Nihal De Silva", "637"), false);

        while (true) {
            System.out.println("\n-----------------------------------LIBRARY MANAGMENT SYSTEM-----------------------------------");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");      //enter title
                    String title = scanner.nextLine().trim();
                    if (title.isEmpty()) {                      //validation
                        System.out.println("Title cannot be empty. Please try again.");
                        break;
                    }

                    System.out.print("Enter author: ");     //enter author name
                    String author = scanner.nextLine().trim();
                    if (author.isEmpty()) {                     //validation
                        System.out.println("Author cannot be empty. Please try again.");
                        break;
                    }

                    System.out.print("Enter ISBN: ");       //enter ISBN
                    String isbn = scanner.nextLine().trim();
                    if (isbn.isEmpty()) {                       //validatin
                        System.out.println("ISBN cannot be empty. Please try again.");
                        break;
                    }
                    library.addBook(new Book(title, author, isbn), true);
                    break;

                case 2:
                    library.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter ISBN to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    library.borrowBook(borrowIsbn);
                    break;

                case 4:
                    System.out.print("Enter ISBN to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;

                case 5:
                    System.out.println("EXITING... GOODBYE...!");
                    scanner.close();
                    return;

                default:
                    System.out.println("INVALID CHOICE. PLEASE TRY AGAIN.");
            }
        }
    }
}
