package connection;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	  public static Connection getMyConnection() throws SQLException,
      ClassNotFoundException {
  // Sử dụng Oracle.
  // Bạn có thể thay thế bởi Database nào đó.
  return SQLServerConnUtils_SQLJDBC.getSQLServerConnection();
}

}
