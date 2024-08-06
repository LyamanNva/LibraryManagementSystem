import java.util.Scanner;

public class LibraryManagementSystem {
    private static UserService userService = new UserService();
    private static AdminService adminService = new AdminService("admin", "admin123");
    private static User loggedInUser;
    private static boolean isAdmin;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (loggedInUser == null && !isAdmin) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Admin Login");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        registerUser(scanner);
                        break;
                    case 2:
                        loginUser(scanner);
                        break;
                    case 3:
                        loginAdmin(scanner);
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else if (isAdmin) {
                System.out.println("Admin Menu:");
                System.out.println("1. Add Book");
                System.out.println("2. Update Book");
                System.out.println("3. Delete Book");
                System.out.println("4. View All Books");
                System.out.println("5. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addBook(scanner);
                        break;
                    case 2:
                        updateBook(scanner);
                        break;
                    case 3:
                        deleteBook(scanner);
                        break;
                    case 4:
                        viewAllBooks();
                        break;
                    case 5:
                        isAdmin = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("User Menu:");
                System.out.println("1. Issue Book");
                System.out.println("2. Return Book");
                System.out.println("3. View Issued Books");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        issueBook(scanner);
                        break;
                    case 2:
                        returnBook(scanner);
                        break;
                    case 3:
                        viewIssuedBooks();
                        break;
                    case 4:
                        loggedInUser = null;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (userService.registerUser(username, password)) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = userService.loginUser(username, password);
        if (user != null) {
            loggedInUser = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void loginAdmin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        if (username.equals(adminService.getAdmin().getUsername()) && password.equals(adminService.getAdmin().getPassword())) {
            isAdmin = true;
            System.out.println("Admin login successful.");
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        adminService.addBook(title, author);
    }

    private static void updateBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new author: ");
        String author = scanner.nextLine();
        adminService.updateBook(id, title, author);
    }

    private static void deleteBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        adminService.deleteBook(id);
    }

    private static void viewAllBooks() {
        adminService.viewAllBooks();
    }

    private static void issueBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        userService.issueBook(loggedInUser, bookId);
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        userService.returnBook(loggedInUser, bookId);
    }

    private static void viewIssuedBooks() {
        for (Book book : loggedInUser.getIssuedBooks()) {
            System.out.println(book);
        }
    }
}
