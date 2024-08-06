import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Book> issuedBooks;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.issuedBooks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void issueBook(Book book) {
        issuedBooks.add(book);
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
    }
}
