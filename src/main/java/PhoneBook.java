import java.sql.*;

public class PhoneBook {
    private static final String URL = "jdbc:mysql://localhost:3306/phonebook";
    private static final String USER = "root";
    private static final String PASSWORD = "deni7822";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addContact(String name, String email) {
        String sql = "INSERT INTO contacts (name, email) VALUES (?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Contact added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addContactDetail(int contactId, String type, String detail) {
        String sql = "INSERT INTO contact_details (contact_id, type, detail) VALUES (?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contactId);
            pstmt.setString(2, type);
            pstmt.setString(3, detail);
            pstmt.executeUpdate();
            System.out.println("Contact detail added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateContact(int id, String name, String email) {
        String sql = "UPDATE contacts SET name = ?, email = ? WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Contact updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Contact deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listContacts() {
        String sql = "SELECT * FROM contacts";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addContact("John Doe", "john.doe@example.com");
        phoneBook.addContactDetail(1, "Phone", "123-456-7890");
        phoneBook.listContacts();
    }
}
