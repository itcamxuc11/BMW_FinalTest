package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnUtils_SQLJDBC {
	public static Connection getSQLServerConnection()
	         throws SQLException, ClassNotFoundException {
	     String hostName = "den1.mssql7.gear.host";
	     String sqlInstanceName = "SQLEXPRESS";
	     String database = "bmw";
	     String userName = "bmw";
	     String password = "Qc0K?037C07-";
	 
	     return getSQLServerConnection(hostName, sqlInstanceName,
	             database, userName, password);
	 }
	 
	 // TrÆ°á»�ng há»£p sá»­ dá»¥ng SQLServer.
	 // VÃ  thÆ° viá»‡n SQLJDBC.
	 public static Connection getSQLServerConnection(String hostName,
	         String sqlInstanceName, String database, String userName,
	         String password) throws ClassNotFoundException, SQLException {
	     // Khai bÃ¡o class Driver cho DB SQLServer
	     // Viá»‡c nÃ y cáº§n thiáº¿t vá»›i Java 5
	     // Java6 tá»± Ä‘á»™ng tÃ¬m kiáº¿m Driver thÃ­ch há»£p.
	     // Náº¿u báº¡n dÃ¹ng Java6, thÃ¬ ko cáº§n dÃ²ng nÃ y cÅ©ng Ä‘Æ°á»£c.
	     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 
	     // Cáº¥u trÃºc URL Connection dÃ nh cho SQLServer
	     // VÃ­ dá»¥:
	     // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr
	     String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
	             + ";instance=" + sqlInstanceName + ";databaseName=" + database;
	 
	     Connection conn = DriverManager.getConnection(connectionURL, userName,
	             password);
	     return conn;
	 }
}
