import java.sql.*;

public class StudentDAO {
    public static void insertStudent(Student s) {
        try {
            // Laster inn MySQL-driveren manuelt
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", ""
            );

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO students (name, age) VALUES (?, ?)"
            );

            stmt.setString(1, s.getName());
            stmt.setInt(2, s.getAge());
            stmt.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
