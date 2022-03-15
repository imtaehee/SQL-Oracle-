package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class EmpManager04 {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
    public void printEmployee(String jobs[]) throws SQLException{
		
		String dburl="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection con=DriverManager.getConnection(dburl, "hr","hr");
		
		/*
		Statement stmt=con.createStatement();
		
		String query="SELECT\r\n"
				+ "E.EMPLOYEE_ID, E.FIRST_NAME, E.SALARY\r\n"
				+ "FROM EMPLOYEES E, JOBS J\r\n"
				+ "WHERE E.JOB_ID=J.JOB_ID AND "
				+ "J.JOB_TITLE IN ('" + jobs[0]+ "', '"+jobs[1]+"')";
		
		ResultSet rs=stmt.executeQuery(query);
		*/
		
		String query="SELECT\r\n"
				+ "E.EMPLOYEE_ID, E.FIRST_NAME, E.SALARY\r\n"
				+ "FROM EMPLOYEES E, JOBS J\r\n"
				+ "WHERE E.JOB_ID=J.JOB_ID AND "
				+ "J.JOB_TITLE IN (?,?)";
		PreparedStatement pstmt=con.prepareStatement(query);
		
		pstmt.setString(1,jobs[0]);
		pstmt.setString(2,jobs[1]);
		
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
		}
		
		rs.close();
		//stmt.close();
		pstmt.close();
		con.close();
		
	}

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		
		String[] jobs= {"Accountant", "Stock Clerk"};
		new EmpManager04().printEmployee(jobs);
	
		
	}
}
