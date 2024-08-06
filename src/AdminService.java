public class AdminService {
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public AdminService(String username, String password) {
        this.admin = new Admin(username, password);
    }

    public void addBook(String title, String author) {
        Book book = new Book(Database.bookIdCounter++, title, author);
        Database.books.put(book.getId(), book);
        System.out.println("Book added: " + book);
    }

    public void updateBook(int id, String title, String author) {
        Book book = Database.books.get(id);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            System.out.println("Book updated: " + book);
        } else {
            System.out.println("Book does not exist.");
        }
    }

    public void deleteBook(int id) {
        Book book = Database.books.remove(id);
        if (book != null) {
            System.out.println("Book deleted: " + book);
        } else {
            System.out.println("Book does not exist.");
        }
    }

    public void viewAllBooks() {
        for (Book book : Database.books.values()) {
            System.out.println(book);
        }
    }
}
