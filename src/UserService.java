public class UserService {
    public boolean registerUser(String username, String password) {
        if (Database.users.containsKey(username)) {
            return false;
        }
        Database.users.put(username, new User(username, password));
        return true;
    }

    public User loginUser(String username, String password) {
        User user = Database.users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void issueBook(User user, int bookId) {
        Book book = Database.books.get(bookId);
        if (book != null && !book.isIssued()) {
            book.setIssued(true);
            user.issueBook(book);
            System.out.println("Book issued: " + book);
        } else {
            System.out.println("Book is already issued or does not exist.");
        }
    }

    public void returnBook(User user, int bookId) {
        Book book = Database.books.get(bookId);
        if (book != null && book.isIssued()) {
            book.setIssued(false);
            user.returnBook(book);
            System.out.println("Book returned: " + book);
        } else {
            System.out.println("Book was not issued or does not exist.");
        }
    }
}

