package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EmpStatistics {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void printStatistics(int maxSalary) throws Exception{
		
		System.out.println("===================================");
		System.out.println("Max Salary : "+maxSalary);
		System.out.println("===================================");
		
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection con=DriverManager.getConnection(url,"hr","hr");
		
		Statement stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery("SELECT\r\n"
				+ "J.JOB_TITLE, AVG(E.SALARY)\r\n"
				+ "FROM EMPLOYEES E, JOBS J\r\n"
				+ "WHERE E.JOB_ID=J.JOB_ID and SALARY>="+maxSalary
				+ "GROUP BY JOB_TITLE\r\n"
				+ "ORDER BY AVG(E.SALARY) DESC ");
		
		while(rs.next()) {
			System.out.println("["+rs.getString(1)+"]"+ rs.getInt(2));
		}
	}

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		
		int maxSalary=10000;
		try {
			new EmpStatistics().printStatistics(maxSalary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		maxSalary=15000;
		try {
			new EmpStatistics().printStatistics(maxSalary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
