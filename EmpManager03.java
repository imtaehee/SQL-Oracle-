package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpManager03 {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void printEmployee(String name, int salary) throws SQLException{
		
		String dburl="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection con=DriverManager.getConnection(dburl, "hr","hr");
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT EMPLOYEE_ID, FIRST_NAME, SALARY FROM EMPLOYEES WHERE SALARY>"+salary+" AND UPPER(FIRST_NAME) LIKE UPPER('%"+name+"%')");
		
		while(rs.next()) {
			int id=rs.getInt(1);
			String firstName=rs.getString(2);
			int sal=rs.getInt(3);
			System.out.println(id+" "+firstName+" "+sal);
		}
		
	}

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		
		new EmpManager03().printEmployee("al", 4000);
		
	}

}
