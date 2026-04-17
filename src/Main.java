import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "RIBERA";
    private static final String PASS = "ribera";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            ejecutar(conn);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    public static void ejecutar(Connection conn) throws SQLException {
        String sqlDept = "INSERT INTO departamento (id, nombre) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlDept)) {
            pstmt.setInt(1, 10);
            pstmt.setString(2, "Sistemas");
            pstmt.executeUpdate();
        }

        String sqlEmp = "INSERT INTO empleado (id, nombre, salario, dep_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlEmp)) {
            pstmt.setInt(1, 101);
            pstmt.setString(2, "Ana Garcia");
            pstmt.setDouble(3, 3500.00);
            pstmt.setInt(4, 10);
            pstmt.executeUpdate();
        }
        System.out.println("¡Datos insertados con éxito usando los nombres correctos!");
    }
}