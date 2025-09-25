import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

class User {
    private String name;
    private List<Book> issuedBooks;

    public User(String name) {
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void issueBook(Book book) {
        issuedBooks.add(book);
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public boolean issueBook(String title, User user) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isIssued()) {
                book.issue();
                user.issueBook(book);
                System.out.println(user.getName() + " issued " + book.getTitle());
                return true;
            }
        }
        System.out.println("Book not available: " + title);
        return false;
    }

    public boolean returnBook(String title, User user) {
        for (Book book : user.getIssuedBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                user.returnBook(book);
                System.out.println(user.getName() + " returned " + book.getTitle());
                return true;
            }
        }
        System.out.println("Book not found in user's issued list: " + title);
        return false;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();

    
        library.addBook(new Book("Java Programming", "James Gosling"));
        library.addBook(new Book("Clean Code", "Robert C. Martin"));
        library.addBook(new Book("Effective Java", "Joshua Bloch"));

    
        User user1 = new User("Swapnil");

    
        System.out.println("Available books:");
        library.displayBooks();

    
        library.issueBook("Clean Code", user1);

    
        library.issueBook("Clean Code", user1);

    
        library.returnBook("Clean Code", user1);

    
        System.out.println("\nAfter transactions:");
        library.displayBooks();
    }
}
